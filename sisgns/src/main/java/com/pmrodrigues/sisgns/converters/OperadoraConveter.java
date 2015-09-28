package com.pmrodrigues.sisgns.converters;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.ioc.RequestScoped;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.repositories.OperadoraRepository;
import com.pmrodrigues.vraptor.crud.converters.AbstractTypeConverter;

/**
 * Created by Marceloo on 28/09/2015.
 */
@Convert(Operadora.class)
@RequestScoped
public class OperadoraConveter extends AbstractTypeConverter<Operadora> {
    public OperadoraConveter(final OperadoraRepository repository) {
        super(repository);
    }
}
