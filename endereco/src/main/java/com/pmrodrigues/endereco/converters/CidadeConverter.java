package com.pmrodrigues.endereco.converters;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.ioc.RequestScoped;
import com.pmrodrigues.endereco.models.Cidade;
import com.pmrodrigues.endereco.repositories.CidadeRepository;
import com.pmrodrigues.vraptor.crud.converters.AbstractTypeConverter;

/**
 * Created by Marceloo on 24/09/2015.
 */
@Convert(Cidade.class)
@RequestScoped
public class CidadeConverter extends AbstractTypeConverter<Cidade> {
    public CidadeConverter(CidadeRepository repository) {
        super(repository);
    }
}
