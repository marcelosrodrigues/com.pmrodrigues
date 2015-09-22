package com.pmrodrigues.vraptor.crud.resolvers.crud;

import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.http.route.RoutesConfiguration;
import br.com.caelum.vraptor.http.route.Rules;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Set;

import static java.lang.String.format;

/**
 * Created by Marceloo on 09/12/2014.
 */
@Component
@ApplicationScoped
public class CRUDRoutes implements RoutesConfiguration {

    private static final Logger logging = Logger.getLogger(CRUDRoutes.class);

    @Override
    public void config(final Router router) {

        final Reflections reflections = new Reflections();
        final Set<Class<? extends AbstractCRUDController>> classes = reflections.getSubTypesOf(AbstractCRUDController.class);

        logging.debug("montando a tabela de roteamento para as classes crud");

        new Rules(router) {
            @Override
            public void routes() {
                for (final Class<? extends AbstractCRUDController> clazz : classes) {

                    logging.debug(format("montando o roteamento para classe %s", clazz.getName()));

                    final ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
                    final Class persistedType = (Class) type.getActualTypeArguments()[0];
                    final String entityName = persistedType.getSimpleName().toLowerCase();

                    for (final Method metodo : clazz.getMethods()) {

                        if (!metodo.getDeclaringClass().equals(Object.class)) {
                            RouteConfig config = RouteFactory.create(metodo);

                            if (logging.isDebugEnabled()) {
                                logging.debug(format("montando o roteamento para classe %s method %s URL %s",
                                        clazz.getName(), metodo.getName(),
                                        config.getURL()));
                            }


                            routeFor(format(config.getURL(), entityName))
                                    .with(config.getHttpMethod())
                                    .is(clazz, config.getMethod());
                        }

                    }

                }
            }
        };
    }
}
