package com.pmrodrigues.endereco.converters;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.ioc.RequestScoped;
import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.endereco.repositories.LogradouroRepository;
import com.pmrodrigues.vraptor.crud.converters.AbstractTypeConverter;

/**
 * Created by Marceloo on 24/09/2015.
 */
@Convert(Logradouro.class)
@RequestScoped
public class LogradouroConverter extends AbstractTypeConverter<Logradouro> {
    public LogradouroConverter(LogradouroRepository repository) {
        super(repository);
    }
}
