package com.pmrodrigues.endereco.converters;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.ioc.RequestScoped;
import com.pmrodrigues.endereco.models.Bairro;
import com.pmrodrigues.endereco.repositories.BairroRepository;
import com.pmrodrigues.vraptor.crud.converters.AbstractTypeConverter;

/**
 * Created by Marceloo on 24/09/2015.
 */
@Convert(Bairro.class)
@RequestScoped
public class BairroConverter extends AbstractTypeConverter<Bairro> {
    public BairroConverter(BairroRepository repository) {
        super(repository);
    }
}
