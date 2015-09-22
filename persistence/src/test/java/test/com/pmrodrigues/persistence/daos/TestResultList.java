package test.com.pmrodrigues.persistence.daos;

import com.pmrodrigues.persistence.daos.ResultList;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.persistence.models.Loja;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Marceloo on 14/04/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestResultList extends
        AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    private final Long QUANTIDADE_MAXIMA_REGISTRO = 101L;

    @Before
    public void before() {

        after();
        int id = this.jdbcTemplate.queryForObject("select max(id) + 1 from loja", Integer.class);
        final List<Object[]> lojas = new ArrayList<>();
        for (int i = id; i < QUANTIDADE_MAXIMA_REGISTRO; i++) {
            lojas.add(new Object[]{i, String.format("TESTE_%d", i)});
        }

        jdbcTemplate.batchUpdate("insert into loja (id ,nome) values (?,?)", lojas);

    }

    @After
    public void after() {
        jdbcTemplate.update("delete from loja where nome like 'TESTE%'");
    }

    @Test
    public void doSearch() {


        final ResultList<Loja> lojas = new ResultList(this.sessionFactory
                .getCurrentSession()
                .createCriteria(Loja.class)
                .addOrder(Order.asc("id")))
                .comTamanhoPagina(10)
                .naPagina(0);

        long quantidadeRegistros = jdbcTemplate.queryForObject("select count(1) from loja", Long.class);
        long quantidadePaginas = (quantidadeRegistros / 10);
        if (quantidadeRegistros % 10 > 0) {
            quantidadePaginas++;
        }

        assertEquals(10, lojas.getConsulta().size());
        assertEquals(quantidadePaginas, lojas.getQuantidadeDePaginas());
    }

    @Test
    public void hasNext() {
        final ResultList<Loja> lojas = new ResultList(this.sessionFactory
                .getCurrentSession()
                .createCriteria(Loja.class)
                .addOrder(Order.asc("id")))
                .comTamanhoPagina(10)
                .naPagina(0);

        assertTrue(lojas.temProximaPagina());
        assertEquals(1, lojas.getNumeroDaPagina());

        assertFalse(lojas.naUltimaPage().temProximaPagina());

        assertTrue(lojas.naPagina(8).temProximaPagina());

    }

    @Test
    public void hasPrevious() {
        final ResultList<Loja> lojas = new ResultList(this.sessionFactory
                .getCurrentSession()
                .createCriteria(Loja.class)
                .addOrder(Order.asc("id")))
                .comTamanhoPagina(10)
                .naUltimaPage();

        assertTrue(lojas.temPaginaAnterior());
        assertEquals(8L, lojas.getNumeroDaPagina());

        assertFalse(lojas.naPrimeiraPagina().temPaginaAnterior());
        assertTrue(lojas.naPagina(1).temPaginaAnterior());
    }
}
