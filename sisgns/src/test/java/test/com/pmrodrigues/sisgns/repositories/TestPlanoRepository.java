package test.com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.models.Plano;
import com.pmrodrigues.sisgns.repositories.PlanoRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 16/11/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestPlanoRepository extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private PlanoRepository repository;


    @Before
    public void setup() {

        for (int i = 0; i < 5; i++) {
            jdbcTemplate.update("insert into plano ( nome, operadora_id, administradora_id) values ( ? , ? , ?)",
                    format("PLANO_%s", i), 54, 1);
        }

    }

    @Test
    public void devePesquisarPlanosPeloNome() {


        final Operadora operadora = new Operadora();
        operadora.setId(54L);
        final ResultList<Plano> planos = repository.buscarPlanosPeloNome(operadora, "PLANO");
        long count = jdbcTemplate.queryForObject("select count(1) from plano where nome like 'PLANO%'", Long.class);
        assertEquals(count, planos.getQuantidadeRegistros());

    }

}