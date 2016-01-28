package test.com.pmrodrigues.sisgns.eventlisteners;

import com.pmrodrigues.security.event.UserUpdateEvent;
import com.pmrodrigues.sisgns.eventlisteners.UserUpdateEventListener;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

/**
 * Created by Marceloo on 28/01/2016.
 */
public class TestUserUpdateEventListener {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    @Test
    public void testOnApplicationEvent() throws Exception {

        final UsuarioRepository repository = context.mock(UsuarioRepository.class);

        context.checking(new Expectations() {{
            oneOf(repository).set(with(aNonNull(Usuario.class)));
        }});

        final UserUpdateEventListener eventListener = new UserUpdateEventListener(repository);
        eventListener.onApplicationEvent(new UserUpdateEvent(new Usuario()));

    }
}