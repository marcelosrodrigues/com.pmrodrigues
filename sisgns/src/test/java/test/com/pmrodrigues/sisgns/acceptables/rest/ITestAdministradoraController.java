package test.com.pmrodrigues.sisgns.acceptables.rest;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.sisgns.models.Administradora;
import org.apache.http.HttpStatus;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;

import static com.jayway.restassured.RestAssured.given;
import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static test.com.pmrodrigues.sisgns.utilities.GeradorCNPJCPF.cnpj;


/**
 * Created by Marceloo on 09/12/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class ITestAdministradoraController extends AbstractTransactionalJUnit4SpringContextTests {

    private Administradora alacoro;

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void preparaBancoDeTeste() {

        final Logradouro enderecoDaAlacoro = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();

        this.alacoro = AdministradoraBuilder.getFactory()
                .comNome("ALACORO")
                .comEndereco(enderecoDaAlacoro)
                .comCNPJ(cnpj()).criar();

        this.sessionFactory.getCurrentSession().persist(alacoro);
        this.sessionFactory.getCurrentSession().createSQLQuery("COMMIT").executeUpdate();

    }

    @After
    public void limparABaseDeDados() {

        if (this.alacoro != null) {
            this.sessionFactory.getCurrentSession().delete(alacoro);
            this.sessionFactory.getCurrentSession().createSQLQuery("COMMIT").executeUpdate();
        }

    }


    @Test
    public void devePesquisarAdministradora() {

        final Administradora administradora = given()
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .get("/administradora/ALA.json")
                .andReturn()
                .jsonPath().getObject("list[0]", Administradora.class);

        assertNotNull(administradora);
        assertEquals(alacoro, administradora);
    }

    @Test
    public void naoPodeRetornarAdministradoraQueNaoExiste() {

        given()
                .expect()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .get("/administradora/XXX.json");
    }
}

