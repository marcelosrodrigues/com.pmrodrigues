package com.pmrodrigues.sisgns.securities;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.security.Filtro;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.FiltroRepository;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;

import static java.lang.String.format;

/**
 * Created by Marceloo on 11/12/2015.
 */
@Intercepts
@RequestScoped
public class HibernateRequestInterceptor implements Interceptor {

    private static final Logger logging = Logger.getLogger(HibernateRequestInterceptor.class);


    private final SessionFactory sessionFactory;
    private final FiltroRepository filtroRepository;
    private final UsuarioRepository usuarioRepository;
    private final HttpServletRequest request;
    private final Session session;

    public HibernateRequestInterceptor(final SessionFactory sessionFactory, final FiltroRepository filtroRepository,
                                       final UsuarioRepository usuarioRepository,
                                       final HttpServletRequest request) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.getCurrentSession();
        this.filtroRepository = filtroRepository;
        this.usuarioRepository = usuarioRepository;
        this.request = request;
    }

    @Override
    public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {

        final Usuario usuario = usuarioRepository.findByEmail(request.getRemoteUser());

        logging.debug(format("Pesquisando os filtros para o usuário %s", usuario));

        final ResultList<Filtro> filtrosDeDados = filtroRepository.buscarFiltrosHabilitadosParaOUsuario(usuario).todos();

        logging.debug(format("Para o usuário %s encontramos os filtros %s ", usuario, filtrosDeDados));

        for (final Filtro filtroDeDados : filtrosDeDados.getConsulta()) {

            logging.debug(format("habilitando o filtro %s", filtroDeDados));

            session.enableFilter(filtroDeDados.getNome())
                    .setParameter("usuario", usuario.getId());
        }

        stack.next(method, resourceInstance);

    }

    @Override
    public boolean accepts(ResourceMethod method) {
        return true;
    }
}
