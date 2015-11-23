package com.pmrodrigues.sisgns.converters;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.ioc.RequestScoped;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.repositories.ModalidadeRepository;
import com.pmrodrigues.vraptor.crud.converters.AbstractTypeConverter;

/**
 * Created by Marceloo on 23/11/2015.
 */
@Convert(Modalidade.class)
@RequestScoped
public class ModalidadeConverter extends AbstractTypeConverter<Modalidade> {
    public ModalidadeConverter(final ModalidadeRepository repository) {
        super(repository);
    }
}
