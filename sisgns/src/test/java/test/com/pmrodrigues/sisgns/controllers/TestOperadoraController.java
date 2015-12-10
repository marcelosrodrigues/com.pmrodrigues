package test.com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.controllers.OperadoraController;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.repositories.OperadoraRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.OperadoraBuilder;

import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 29/09/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestOperadoraController extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private OperadoraRepository repository;

    @Autowired
    private SessionFactory sessionFactory;

    private Result result = new MockResult();

    private MockValidator validator = new MockValidator();

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

        jdbcTemplate.update("insert into operadora (nome) VALUES  ('UNIMED')");
    }

    @Test
    public void deveBuscarOperadoraPeloNome() throws Exception {

        final OperadoraController controller = new OperadoraController(repository, result, validator);
        ResultList<Operadora> resultList = controller.buscarOperadoraPeloNome("UNIMED");


        final long count = jdbcTemplate.queryForObject("select count(1) from operadora where nome like 'UNIMED%' and excluido = 0", Long.class);
        assertEquals(count, resultList.getQuantidadeRegistros());
    }

    @Test(expected = ValidationException.class)
    public void naoPodeSalvarOperadoraComDadosInvalidos() {
        final Operadora operadora = OperadoraBuilder.getFactory()
                .comNome("")
                .comCodigo("")
                .criar();
        final OperadoraController controller = new OperadoraController(repository, result, validator);

        controller.salvar(operadora);
    }

    @Test
    public void deveSalvarOperadora() {

        final Operadora operadora = OperadoraBuilder.getFactory()
                .comAdministradora(administradora)
                .comModalidade((Modalidade) sessionFactory.getCurrentSession().get(Modalidade.class, 1L))
                .criar();
        final OperadoraController controller = new OperadoraController(repository, result, validator);

        controller.salvar(operadora);

    }
}