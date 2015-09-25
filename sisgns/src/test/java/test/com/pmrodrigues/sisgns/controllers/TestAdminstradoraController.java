package test.com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import com.pmrodrigues.endereco.models.Telefone;
import com.pmrodrigues.sisgns.controllers.AdminstradoraController;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.repositories.AdministradoraRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

/**
 * Created by Marceloo on 24/09/2015.
 */
public class TestAdminstradoraController {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    @Test
    public void deveIncluirAdministradora() throws Exception {

        final MockResult result = new MockResult();
        final MockValidator validator = new MockValidator();
        final AdministradoraRepository repository = context.mock(AdministradoraRepository.class);

        final AdminstradoraController controller = new AdminstradoraController(repository, result, validator);

        context.checking(new Expectations() {{
            oneOf(repository).add(with(aNonNull(Administradora.class)));
        }});

        controller.salvar(new Administradora(), new Telefone(), new Telefone(), new Telefone());


    }
}