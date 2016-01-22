package test.com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Corretor;
import com.pmrodrigues.sisgns.repositories.CorretorRepository;
import com.pmrodrigues.vraptor.crud.exceptions.UniqueException;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;

import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 09/10/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestCorretorRepository extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CorretorRepository repository;

    @Autowired
    private SessionFactory sessionFactory;
    private Administradora administradora;

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

        sessionFactory.getCurrentSession().persist(new Corretor("teste", "teste@teste.com", "12345678", administradora));

    }

    @Test
    public void deveAdicionar() throws Exception {

        Corretor corretor = new Corretor();
        corretor.setEmail("corretor@corretor.com");
        corretor.setNome("TESTE");
        corretor.setBloqueado(false);
        corretor.setPassword("12345678");
        corretor.setAdministradora(administradora);

        repository.add(corretor);

        long count = jdbcTemplate.queryForObject("select count(1) from usuario where email = ? and dtype = 'Corretor'",
                Long.class, corretor.getEmail());

        assertEquals(1L, count);

    }

    @Test(expected = UniqueException.class)
    public void naoPodeSalvarOCorretor() {
        Corretor corretor = new Corretor();
        corretor.setEmail("teste@teste.com");
        corretor.setNome("TESTE");
        corretor.setBloqueado(false);
        corretor.setPassword("12345678");

        repository.add(corretor);
    }

    @Test
    public void deveAtualizarOCorretor() {
        long id = jdbcTemplate.queryForObject("select id from usuario where dtype = 'Corretor' and email = ?",
                Long.class, "teste@teste.com");
        Corretor corretor = new Corretor();
        corretor.setId(id);
        corretor.setEmail("corretor@corretor.com");
        corretor.setNome("TESTE");
        corretor.setBloqueado(false);
        corretor.setPassword("12345678");

        repository.set(corretor);

        sessionFactory.getCurrentSession().flush();

        long count = jdbcTemplate.queryForObject("select id from usuario where email = ? and dtype = 'Corretor'",
                Long.class, corretor.getEmail());

        assertEquals(id, count);
    }

    @Test
    public void deveAtualizarOCorretorSemAlterarOEmail() {
        long id = jdbcTemplate.queryForObject("select id from usuario where dtype = 'Corretor' and email = ?",
                Long.class, "teste@teste.com");
        Corretor corretor = new Corretor();
        corretor.setId(id);
        corretor.setEmail("teste@teste.com");
        corretor.setNome("TESTE");
        corretor.setBloqueado(false);
        corretor.setPassword("12345678");

        repository.set(corretor);

        sessionFactory.getCurrentSession().flush();

        long count = jdbcTemplate.queryForObject("select id from usuario where email = ? and dtype = 'Corretor'",
                Long.class, corretor.getEmail());

        assertEquals(id, count);
    }

    @Test(expected = UniqueException.class)
    public void naoPodeAtualizarOCorretor() {

        jdbcTemplate.update("insert into usuario ( DTYPE ,  nome , email , password , bloqueado , tentativas ) values " +
                "( 'Corretor' , 'teste' , 'teste@teste.com.com' , md5('12345678') , false , 0)");


        long id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Long.class);

        Corretor corretor = new Corretor();
        corretor.setId(id);
        corretor.setEmail("teste@teste.com");
        corretor.setNome("TESTE");
        corretor.setBloqueado(false);
        corretor.setPassword("12345678");

        repository.set(corretor);

    }

    @Test
    public void devePesquisarCorretorPeloNome() {

        ResultList<Corretor> corretores = repository.buscarCorretorPorNome("teste");

        long count = jdbcTemplate.queryForObject("select count(1) from usuario where DTYPE = 'Corretor' and nome like 'teste%'", Long.class);

        assertNotNull(corretores);
        assertEquals(count, corretores.getQuantidadeRegistros());
    }
}