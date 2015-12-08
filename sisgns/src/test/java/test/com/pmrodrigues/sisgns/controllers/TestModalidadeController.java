package test.com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.controllers.ModalidadeController;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.repositories.ModalidadeRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.ModalidadeBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 08/12/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestModalidadeController extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ModalidadeRepository repository;

    @Autowired
    private SessionFactory sessionFactory;

    private Result result = new MockResult();

    private Validator validator = new MockValidator();

    private ModalidadeController controller;

    @Before
    public void setup() {

        ModalidadeBuilder.getFactory(sessionFactory)
                .comNome("TESTE 1")
                .comCodigo("0001")
                .criar();

        ModalidadeBuilder.getFactory(sessionFactory)
                .comNome("TESTE 2")
                .comCodigo("0002")
                .criar();

        ModalidadeBuilder.getFactory(sessionFactory)
                .comNome("TESTE 3")
                .comCodigo("0003")
                .criar();

        controller = new ModalidadeController(repository, result, validator);
    }

    @Test
    public void devePesquisarModalidadesPeloNome() {

        ResultList<Modalidade> resultList = controller.buscarModalidePorNome("TESTE");
        assertEquals(3L, resultList.getQuantidadeRegistros());

    }

}