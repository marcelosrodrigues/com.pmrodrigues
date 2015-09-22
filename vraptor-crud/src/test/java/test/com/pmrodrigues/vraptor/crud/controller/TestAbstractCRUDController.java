package test.com.pmrodrigues.vraptor.crud.controller;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.vraptor.crud.Constante;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 02/01/2015.
 */
public class TestAbstractCRUDController {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    private MockResult result = new MockResult();
    private MockValidator validation = new MockValidator();
    private Repository<Loja> repository = context.mock(Repository.class);

    @Test
    public void doDelete() {
        final LojaController controller = new LojaController(repository, result, validation);
        final Loja loja = new Loja();
        loja.setNome("TESTE");

        context.checking(new Expectations() {{
            oneOf(repository).remove(with(aNonNull(Loja.class)));
        }});

        controller.delete(loja);

        assertNotNull(result.included(Constante.SUCESSO));
    }


    @Test
    public void doInsert() {
        final LojaController controller = new LojaController(repository, result, validation);
        final Loja loja = new Loja();
        loja.setNome("TESTE");

        context.checking(new Expectations() {{
            oneOf(repository).add(with(aNonNull(Loja.class)));
        }});

        controller.salvar(loja);

        assertNotNull(result.included(Constante.SUCESSO));
    }

    @Test
    public void doUpdate() {

        final LojaController controller = new LojaController(repository, result, validation);
        final Loja loja = new Loja();
        loja.setNome("TESTE");
        loja.setId(1L);
        context.checking(new Expectations() {{
            oneOf(repository).set(with(aNonNull(Loja.class)));
        }});

        controller.salvar(loja);

        assertNotNull(result.included(Constante.SUCESSO));

    }

    @Test
    public void doSearch() {
        final LojaController controller = new LojaController(repository, result, validation);
        final ResultList<Loja> resultlist = context.mock(ResultList.class);

        context.checking(new Expectations() {{

            oneOf(repository).search(with(aNonNull(Loja.class)));
            will(returnValue(resultlist));

            oneOf(resultlist).naPagina(with(aNonNull(Integer.class)));
            will(returnValue(resultlist));
        }});

        controller.index();

        assertNotNull(result.included(Constante.RESULT_LIST));
    }

    @Test
    public void doSearchWithParameters() {
        final LojaController controller = new LojaController(repository, result, validation);
        final ResultList<Loja> resultlist = context.mock(ResultList.class);

        context.checking(new Expectations() {{

            oneOf(repository).search(with(aNonNull(Loja.class)));
            will(returnValue(resultlist));

            oneOf(resultlist).naPagina(with(aNonNull(Integer.class)));
            will(returnValue(resultlist));

        }});

        controller.search(0, new Loja());

        assertNotNull(result.included(Constante.RESULT_LIST));
    }

    @Test
    public void doFormulario() {

        final LojaController controller = new LojaController(repository, result, validation);
        controller.formulario();

        assertNotNull(result.included(Constante.OBJECT));
    }

    @Test
    public void doOpen() {
        final LojaController controller = new LojaController(repository, result, validation);
        final Loja loja = new Loja();

        context.checking(new Expectations() {{

            oneOf(repository).findById(with(aNonNull(Serializable.class)));
            will(returnValue(loja));
        }});

        controller.show(1L);

        assertNotNull(result.included(Constante.OBJECT));
    }

    ;

    @Test
    public void doPreExecute() {

        final MockCrudController controller = new MockCrudController(repository, result, validation);

        context.checking(new Expectations() {{
            allowing(repository);
        }});

        controller.index();

        assertNotNull(result.included("teste"));

    }

    @Test
    public void doPostExecute() {

        final MockCrudController controller = new MockCrudController(repository, result, validation);

        final Loja loja = new Loja();
        loja.setNome("TESTE");

        context.checking(new Expectations() {{
            allowing(repository);
        }});

        controller.salvar(loja);

        assertNotNull(result.included("teste"));
    }

    @Test
    public void delegateInsert() {

        final MockCrudController controller = new MockCrudController(repository, result, validation);

        final Loja loja = new Loja();
        loja.setNome("TESTE");

        context.checking(new Expectations() {{
            allowing(repository);
        }});

        controller.salvar(loja);

        assertNotNull(result.included("teste"));
    }

    @Test
    public void delegateUpdate() {

        final MockCrudController controller = new MockCrudController(repository, result, validation);

        final Loja loja = new Loja();
        loja.setNome("TESTE");
        loja.setId(1L);

        context.checking(new Expectations() {{
            allowing(repository);
        }});

        controller.salvar(loja);

        assertNotNull(result.included("teste"));
    }
}
