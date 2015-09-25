package com.pmrodrigues.endereco.converters;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.ioc.RequestScoped;
import com.pmrodrigues.endereco.models.Estado;
import com.pmrodrigues.endereco.repositories.EstadoRepository;
import com.pmrodrigues.vraptor.crud.converters.AbstractTypeConverter;

/**
 * Created by Marceloo on 24/09/2015.
 */
@Convert(Estado.class)
@RequestScoped
public class EstadoConverter extends AbstractTypeConverter<Estado> {

    public EstadoConverter(final EstadoRepository repository) {
        super(repository);
    }
}
