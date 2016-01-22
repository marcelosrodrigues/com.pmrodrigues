package test.com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.models.Plano;
import com.pmrodrigues.sisgns.repositories.PlanoRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.OperadoraBuilder;
import test.com.pmrodrigues.sisgns.builders.PlanoBuilder;

import static java.lang.String.format;
import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 16/11/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestPlanoRepository extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private PlanoRepository repository;

    @Autowired
    private SessionFactory sessionFactory;
    private Administradora administradora;
    private Operadora operadora;

    @Before
    public void setup() {

        Logradouro campoDaAreia = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();

        this.administradora = AdministradoraBuilder.getFactory()
                .comEndereco(campoDaAreia)
                .criar();
        sessionFactory.getCurrentSession().persist(administradora);

        this.operadora = OperadoraBuilder.getFactory()
                .comNome("")
                .comCodigo("")
                .comModalidade((Modalidade) sessionFactory.getCurrentSession().get(Modalidade.class, 1L))
                .comAdministradora(administradora)
                .criar();

        sessionFactory.getCurrentSession().persist(operadora);

        for (int i = 0; i < 5; i++) {

            sessionFactory.getCurrentSession().persist(PlanoBuilder.getFactory()
                    .comNome(format("PLANO_%s", i))
                    .comOperadora(operadora)
                    .comAdminstradora(administradora).criar());


        }

    }

    @Test
    public void devePesquisarPlanosPeloNome() {

        final ResultList<Plano> planos = repository.buscarPlanosPeloNome(operadora, "PLANO");
        long count = jdbcTemplate.queryForObject("select count(1) from plano where nome like 'PLANO%'", Long.class);
        assertEquals(count, planos.getQuantidadeRegistros());

    }

}