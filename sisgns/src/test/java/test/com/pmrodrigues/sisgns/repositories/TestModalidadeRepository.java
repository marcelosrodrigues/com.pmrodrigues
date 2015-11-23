package test.com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.repositories.ModalidadeRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 23/11/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestModalidadeRepository extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ModalidadeRepository repository;

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
}