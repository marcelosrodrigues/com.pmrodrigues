package com.pmrodrigues.sisgns.converters;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.ioc.RequestScoped;
import com.pmrodrigues.sisgns.models.Corretor;
import com.pmrodrigues.sisgns.repositories.CorretorRepository;
import com.pmrodrigues.vraptor.crud.converters.AbstractTypeConverter;

/**
 * Created by Marceloo on 08/10/2015.
 */
@Convert(Corretor.class)
@RequestScoped
public class CorretorConverter extends AbstractTypeConverter<Corretor> {
    public CorretorConverter(final CorretorRepository repository) {
        super(repository);
    }
}
