package test.com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.controllers.CorretorController;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.Corretor;
import com.pmrodrigues.sisgns.repositories.CorretorRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.CorretorBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 08/12/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestCorretorController extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CorretorRepository repository;

    @Autowired
    private SessionFactory sessionFactory;


    private MockResult result = new MockResult();
    private MockValidator validator = new MockValidator();
    private CorretorController controller;


    @Before
    public void setup() {

        this.controller = new CorretorController(repository, result, validator);
        Administradora administradora = AdministradoraBuilder.getFactory(sessionFactory).criar();

        CorretorBuilder.getFactory(sessionFactory)
                .comAdministradora(administradora)
                .comNome("Marcelo")
                .comEmail("teste@teste.com")
                .criar();
        CorretorBuilder.getFactory(sessionFactory).comAdministradora(administradora).comNome("Arthur").comEmail("arthur@teste.com").criar();
        CorretorBuilder.getFactory(sessionFactory).comAdministradora(administradora).comNome("Miguel").comEmail("miguel@teste.com").criar();

    }


    @Test
    public void deveBuscarCorretoresPeloNome() {

        final ResultList<Corretor> resultlist = controller.buscarCorretorPorNome("Marcelo");
        assertEquals(1L, resultlist.getQuantidadeRegistros());
    }
}