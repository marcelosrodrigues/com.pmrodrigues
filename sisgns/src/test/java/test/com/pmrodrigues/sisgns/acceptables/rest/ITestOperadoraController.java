package test.com.pmrodrigues.sisgns.acceptables.rest;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.models.Operadora;
import org.apache.http.HttpStatus;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.ModalidadeBuilder;
import test.com.pmrodrigues.sisgns.builders.OperadoraBuilder;

import static com.jayway.restassured.RestAssured.given;
import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 09/12/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class ITestOperadoraController extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;
    private Operadora amil;

    @Before
    public void preparaBancoDeDados() {

        final Logradouro enderecoDaAlacoro = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();

        Administradora alacoro = AdministradoraBuilder.getFactory()
                .comEndereco(enderecoDaAlacoro)
                .criar();

        Modalidade modalidade = ModalidadeBuilder.getFactory().criar();

        sessionFactory.getCurrentSession().persist(alacoro);
        sessionFactory.getCurrentSession().persist(modalidade);

        this.amil = OperadoraBuilder.getFactory()
                .comAdministradora(alacoro)
                .comModalidade(modalidade)
                .comNome("AMIL")
                .comCodigo("0001")
                .criar();

        sessionFactory.getCurrentSession().persist(amil);
        sessionFactory.getCurrentSession().createSQLQuery("COMMIT").executeUpdate();
    }

    @Test
    public void deveRetornarAOperadora() {

        final Operadora operadora = given()
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .get("/operadora/AMI.json")
                .andReturn()
                .jsonPath().getObject("list[0]", Operadora.class);

        assertNotNull(operadora);
        assertEquals(amil, operadora);

    }

    @Test
    public void naoPodeRetornarDadosDaOperadora() {
        given()
                .expect()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .get("/operadora/XXX.json");
    }

    @After
    public void limparBancoDeDados() {
        if (this.amil != null) {
            sessionFactory.getCurrentSession().createSQLQuery("delete from operadora where id = " + this.amil.getId())
                    .executeUpdate();
            sessionFactory.getCurrentSession().delete(this.amil.getModalidade());
            sessionFactory.getCurrentSession().delete(this.amil.getAdministradora());
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().createSQLQuery("COMMIT").executeUpdate();
        }
    }

}
