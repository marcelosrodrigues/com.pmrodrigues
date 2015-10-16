package com.pmrodrigues.sisgns.converters;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.ioc.RequestScoped;
import com.pmrodrigues.sisgns.models.Plano;
import com.pmrodrigues.sisgns.repositories.PlanoRepository;
import com.pmrodrigues.vraptor.crud.converters.AbstractTypeConverter;

/**
 * Created by Marceloo on 15/10/2015.
 */
@Convert(Plano.class)
@RequestScoped
public class PlanoConverter extends AbstractTypeConverter<Plano> {
    public PlanoConverter(final PlanoRepository repository) {
        super(repository);
    }
}
