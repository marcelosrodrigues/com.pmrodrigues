package test.com.pmrodrigues.security;


import com.pmrodrigues.security.UserAuthenticationManager;
import com.pmrodrigues.security.event.UserUpdateEvent;
import com.pmrodrigues.security.models.Usuario;
import com.pmrodrigues.security.services.UserService;
import com.pmrodrigues.security.utilities.MD5;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.assertNotNull;

public class TestUserAuthenticationManager {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    private final UserService service = context.mock(UserService.class);

    private final ApplicationEventPublisher publisher = context.mock(ApplicationEventPublisher.class);

    private UserAuthenticationManager authenticationManager;

    @Before
    public void before() throws Exception {

        authenticationManager = new UserAuthenticationManager(service, publisher);

    }

    @Test
    public void autenticacaoComSucesso() throws Exception {

        final Usuario user = context.mock(Usuario.class);

        context.checking(new Expectations() {{
            oneOf(service).loadUserByUsername(with(aNonNull(String.class)));
            will(returnValue(user));

            oneOf(user).isAccountNonLocked();
            will(returnValue(true));

            oneOf(user).getPassword();
            will(returnValue(MD5.encrypt("12345678")));

            allowing(user);
        }});

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("marcelosrodrigues@globo.com", "12345678"));
        assertNotNull(authentication);

    }

    @Test(expected = AuthenticationException.class)
    public void usuarioNaoEncontrado() throws Exception {

        context.checking(new Expectations() {{
            oneOf(service).loadUserByUsername(with(aNonNull(String.class)));
            will(throwException(new UsernameNotFoundException("")));
        }});

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("marcelosrodrigues@globo.com", "12345678"));
    }

    @Test(expected = AuthenticationException.class)
    public void usuarioInformandoSenhaInvalida() throws Exception {
        final Usuario user = context.mock(Usuario.class);

        context.checking(new Expectations() {{
            oneOf(service).loadUserByUsername(with(aNonNull(String.class)));
            will(returnValue(user));

            oneOf(user).isAccountNonLocked();
            will(returnValue(true));

            oneOf(user).getPassword();
            will(returnValue(MD5.encrypt("asdfghj")));

            oneOf(user).realizouTentativaInvalida();
            oneOf(publisher).publishEvent(with(aNonNull(UserUpdateEvent.class)));
        }});

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("marcelosrodrigues@globo.com", "12345678"));
    }

    @Test(expected = AuthenticationException.class)
    public void usuarioBloqueado() throws Exception {
        final Usuario user = context.mock(Usuario.class);

        context.checking(new Expectations() {{
            oneOf(service).loadUserByUsername(with(aNonNull(String.class)));
            will(returnValue(user));

            oneOf(user).isAccountNonLocked();
            will(returnValue(false));

        }});

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("marcelosrodrigues@globo.com", "12345678"));
    }

}