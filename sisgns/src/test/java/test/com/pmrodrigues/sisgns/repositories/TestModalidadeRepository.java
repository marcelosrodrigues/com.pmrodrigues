package test.com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.repositories.ModalidadeRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.ModalidadeBuilder;
import test.com.pmrodrigues.sisgns.builders.OperadoraBuilder;

import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 23/11/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestModalidadeRepository extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ModalidadeRepository repository;

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setup() {

        jdbcTemplate.update("insert into modalidade ( nome , codigo ) values ( 'TESTE' , '0001')");

    }


    @Test
    public void deveBuscarModalidadePorNome() throws Exception {

        ResultList<Modalidade> modalidades = repository.buscarModalidadePorNome("000");
        long quantidadeEsperada = jdbcTemplate.queryForObject("select count(1) from modalidade where codigo like '000%'", Long.class);

        assertEquals(quantidadeEsperada, modalidades.getQuantidadeRegistros());


    }

    @Test
    public void devePesquisarModalidadesPeloNome() {
        final Modalidade modalidade = new Modalidade();
        modalidade.setNome("TESTE");

        ResultList<Modalidade> modalidades = repository.search(modalidade);
        long quantidadeEsperada = jdbcTemplate.queryForObject("select count(1) from modalidade where nome like 'TESTE%'", Long.class);

        assertEquals(quantidadeEsperada, modalidades.getQuantidadeRegistros());
    }

    @Test
    public void devePesquisarModalidadeInformandoTodosOsParametros() {
        final Modalidade modalidade = new Modalidade();
        modalidade.setNome("TESTE");
        modalidade.setCodigo("000");

        ResultList<Modalidade> modalidades = repository.search(modalidade);
        long quantidadeEsperada = jdbcTemplate.queryForObject("select count(1) from modalidade where nome like 'TESTE%' and codigo like '000%'", Long.class);

        assertEquals(quantidadeEsperada, modalidades.getQuantidadeRegistros());
    }

    @Test
    public void devePesquisarModalidadesPeloCodigo() {
        final Modalidade modalidade = new Modalidade();
        modalidade.setCodigo("000");

        ResultList<Modalidade> modalidades = repository.search(modalidade);
        long quantidadeEsperada = jdbcTemplate.queryForObject("select count(1) from modalidade where codigo like '000%'", Long.class);

        assertEquals(quantidadeEsperada, modalidades.getQuantidadeRegistros());
    }

    @Test
    public void devePesquisarModalidadeSemInformarParametro() {
        ResultList<Modalidade> modalidades = repository.search(null);
        long quantidadeEsperada = jdbcTemplate.queryForObject("select count(1) from modalidade", Long.class);

        assertEquals(quantidadeEsperada, modalidades.getQuantidadeRegistros());
    }

    @Test
    public void deveApagarModalidade() {

        Modalidade modalidade = ModalidadeBuilder.getFactory().criar();


        Logradouro campoDaAreia = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();

        Administradora administradora = AdministradoraBuilder.getFactory().comEndereco(campoDaAreia).criar();
        Operadora operadora = OperadoraBuilder.getFactory().comModalidade(modalidade).comAdministradora(administradora).criar();
        sessionFactory.getCurrentSession().persist(modalidade);
        sessionFactory.getCurrentSession().persist(administradora);
        sessionFactory.getCurrentSession().persist(operadora);

        sessionFactory.getCurrentSession().flush();

        repository.remove(modalidade);
        sessionFactory.getCurrentSession().flush();

        long count = jdbcTemplate.queryForObject("select count(1) from modalidade where id = ? and excluido = 1", Long.class, modalidade.getId());
        assertEquals(1L, count);

    }
}