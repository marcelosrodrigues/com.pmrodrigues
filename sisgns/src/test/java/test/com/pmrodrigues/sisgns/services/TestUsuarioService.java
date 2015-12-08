package test.com.pmrodrigues.sisgns.services;

import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import com.pmrodrigues.sisgns.services.UsuarioService;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 23/09/2015.
 */

public class TestUsuarioService {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    @Test
    public void deveRetornarUmUsuario() throws Exception {

        final UsuarioRepository repository = context.mock(UsuarioRepository.class);
        final UsuarioService service = new UsuarioService(repository);

        context.checking(new Expectations() {{
            oneOf(repository).findByEmail(with(aNonNull(String.class)));
            will(returnValue(new Usuario()));
        }});


        UserDetails usuario = service.loadUserByUsername("teste@teste.com");
        assertNotNull(usuario);

    }

    @Test(expected = UsernameNotFoundException.class)
    public void naoDeveRetornarUmUsuario() throws Exception {

        final UsuarioRepository repository = context.mock(UsuarioRepository.class);
        final UsuarioService service = new UsuarioService(repository);

        context.checking(new Expectations() {{
            oneOf(repository).findByEmail(with(aNonNull(String.class)));
            will(returnValue(null));
        }});

        service.loadUserByUsername("teste@teste.com");

    }
}