package test.com.pmrodrigues.persistence.daos;

import com.pmrodrigues.persistence.daos.ResultList;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.persistence.models.Loja;

import static org.junit.Assert.*;

@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestRepository
        extends
        AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private LojaRepository repository;

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void before() {
        jdbcTemplate.update("delete from loja where nome = 'TESTE'");
    }


    @Test
    public void testFindById() {

        final Long id = this.jdbcTemplate.queryForObject("select id from loja where nome = 'PROJETANDOO'", Long.class);
        Loja loja = repository.findById(id);

        assertNotNull(loja);

    }

    @Test
    public void testListAll() {

        Long count = this.jdbcTemplate.queryForObject("select count(1) from loja", Long.class);
        ResultList<Loja> resultlist = repository.all();

        assertEquals(count, new Long(resultlist.getQuantidadeRegistros()));

    }

    @Test
    public void testAdd() {

        final Loja loja = new Loja();
        loja.setNome("TESTE");
        repository.add(loja);

        long quantidade = jdbcTemplate.queryForObject("select count(id) from loja where nome = 'TESTE'", Long.class);

        assertEquals(1L, quantidade);

    }

    @Test
    public void testSet() {

        jdbcTemplate.update("insert into loja (id , nome) values ( 10000 , 'TESTE')");
        final Loja loja = repository.findById(10000L);
        loja.setNome("TESTE 2");
        repository.set(loja);

        sessionFactory
                .getCurrentSession()
                .flush();

        long quantidade = jdbcTemplate.queryForObject("select count(id) from loja where nome = 'TESTE 2'", Long.class);

        assertEquals(1L, quantidade);

    }

    @Test
    public void testRemove() {

        jdbcTemplate.update("insert into loja (id , nome) values ( 10000 , 'TESTE')");
        final Loja loja = repository.findById(10000L);

        repository.remove(loja);

        long quantidade = jdbcTemplate.queryForObject("select count(id) from loja where nome = 'TESTE'", Long.class);

        assertNotEquals(0L, quantidade);
    }


}
