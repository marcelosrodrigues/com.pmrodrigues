package test.com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.controllers.OperadoraController;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.repositories.OperadoraRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 29/09/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestOperadoraController extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private OperadoraRepository repository;

    private Result result = new MockResult();

    private Validator validator = new MockValidator();

    @Before
    public void setup() {
        jdbcTemplate.update("delete from plano where operadora_id in ( select id from operadora where nome like 'UNIMED%')");
        jdbcTemplate.update("delete from operadora where nome like 'UNIMED%'");
        jdbcTemplate.update("insert into operadora (nome) VALUES  ('UNIMED')");
    }

    @Test
    public void testBuscarOperadoraPeloNome() throws Exception {

        final OperadoraController controller = new OperadoraController(repository, result, validator);
        ResultList<Operadora> resultList = controller.buscarOperadoraPeloNome("UNIMED");


        final long count = jdbcTemplate.queryForObject("select count(1) from operadora where nome like 'UNIMED%'", Long.class);
        assertEquals(count, resultList.getQuantidadeRegistros());
    }
}