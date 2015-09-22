package com.pmrodrigues.vraptor.crud.resolvers;

import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.DefaultPathResolver;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.annotations.Tiles;

@Component
public class TilesPathResolver extends DefaultPathResolver {

    public TilesPathResolver(final FormatResolver resolver) {
        super(resolver);
    }

    @Override
    public String pathFor(final ResourceMethod method) {

        if (method.getMethod().isAnnotationPresent(Tiles.class)) {
            return method.getMethod().getAnnotation(Tiles.class).definition();
        } else if (method.getResource().getType().isAnnotationPresent(CRUD.class)) {
            return format("%s-template", method.getMethod().getName().toLowerCase());
        } else {
            return super.pathFor(method);
        }

    }

}
