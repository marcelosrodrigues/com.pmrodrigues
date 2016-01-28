package test.com.pmrodrigues.security;


import com.pmrodrigues.security.SuccessHandler;
import com.pmrodrigues.security.event.UserUpdateEvent;
import com.pmrodrigues.security.models.Usuario;
import com.pmrodrigues.security.services.UserService;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestSuccessHandler {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    private final UserService service = context.mock(UserService.class);
    private final ApplicationEventPublisher publisher = context.mock(ApplicationEventPublisher.class);
    private final HttpServletRequest request = context.mock(HttpServletRequest.class);
    private final HttpServletResponse response = context.mock(HttpServletResponse.class);
    private final Authentication authentication = context.mock(Authentication.class);
    private final Usuario usuario = context.mock(Usuario.class);

    @Test
    public void testOnAuthenticationSuccess() throws Exception {

        context.checking(new Expectations() {{

            oneOf(authentication).getPrincipal();
            will(returnValue("teste"));

            oneOf(service).loadUserByUsername(with(aNonNull(String.class)));
            will(returnValue(usuario));

            oneOf(usuario).desbloquear();

            oneOf(publisher).publishEvent(with(aNonNull(UserUpdateEvent.class)));

            oneOf(request).getContextPath();
            will(returnValue(null));

            oneOf(response).sendRedirect(with(aNonNull(String.class)));
        }});

        final SuccessHandler handler = new SuccessHandler(service, publisher);

        handler.onAuthenticationSuccess(request, response, authentication);

    }

    @Test
    public void testOnAuthenticationSuccessComAcertoDeContexto() throws Exception {

        context.checking(new Expectations() {{

            oneOf(authentication).getPrincipal();
            will(returnValue("teste"));

            oneOf(service).loadUserByUsername(with(aNonNull(String.class)));
            will(returnValue(usuario));

            oneOf(usuario).desbloquear();
            oneOf(publisher).publishEvent(with(aNonNull(UserUpdateEvent.class)));

            allowing(request).getContextPath();
            will(returnValue("/teste"));

            oneOf(response).sendRedirect(with(aNonNull(String.class)));
        }});

        final SuccessHandler handler = new SuccessHandler(service, publisher);

        handler.onAuthenticationSuccess(request, response, authentication);

    }
}