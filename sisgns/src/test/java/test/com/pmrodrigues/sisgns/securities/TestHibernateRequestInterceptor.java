package test.com.pmrodrigues.sisgns.securities;

import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.resource.ResourceMethod;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.security.Filtro;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.FiltroRepository;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import com.pmrodrigues.sisgns.securities.HibernateRequestInterceptor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import test.com.pmrodrigues.sisgns.builders.FiltroBuilder;
import test.com.pmrodrigues.sisgns.builders.UsuarioBuilder;

import javax.servlet.http.HttpServletRequest;

import static java.util.Arrays.asList;

/**
 * Created by Marceloo on 11/12/2015.
 */
public class TestHibernateRequestInterceptor {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    private SessionFactory sessionFactory = context.mock(SessionFactory.class);
    private FiltroRepository filtroRepository = context.mock(FiltroRepository.class);
    private UsuarioRepository usuarioRepository = context.mock(UsuarioRepository.class);
    private HttpServletRequest request = context.mock(HttpServletRequest.class);

    @Test
    public void deveHabilitarFiltroParaOUsuarioLogado() {

        final Session session = context.mock(Session.class);
        final Filter filter = context.mock(Filter.class);
        final Usuario usuario = UsuarioBuilder.getFactory().comId(1L).criar();
        final InterceptorStack stack = context.mock(InterceptorStack.class);
        final ResourceMethod method = context.mock(ResourceMethod.class);
        final ResultList<Filtro> resultado = context.mock(ResultList.class);

        context.checking(new Expectations() {{


            oneOf(sessionFactory).getCurrentSession();
            will(returnValue(session));

            oneOf(request).getRemoteUser();
            will(returnValue("marcelosrodrigues@globo.com"));

            oneOf(usuarioRepository).findByEmail(with(aNonNull(String.class)));
            will(returnValue(usuario));

            oneOf(filtroRepository).buscarFiltrosHabilitadosParaOUsuario(with(aNonNull(Usuario.class)));
            will(returnValue(resultado));

            oneOf(resultado).todos();
            will(returnValue(resultado));

            oneOf(resultado).getConsulta();
            will(returnValue(asList(FiltroBuilder.getFactory().criar())));

            oneOf(session).enableFilter(with(aNonNull(String.class)));
            will(returnValue(filter));

            oneOf(filter).setParameter("usuario", 1L);

            oneOf(stack).next(with(aNonNull(ResourceMethod.class)), with(aNonNull(Object.class)));

        }});

        final HibernateRequestInterceptor interceptor = new HibernateRequestInterceptor(sessionFactory, filtroRepository, usuarioRepository, request);
        interceptor.intercept(stack, method, new Object());
    }

}
