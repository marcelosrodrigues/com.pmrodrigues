package com.pmrodrigues.pessoa.converters;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.ioc.RequestScoped;
import com.pmrodrigues.pessoa.models.Pessoa;
import com.pmrodrigues.pessoa.repository.PessoaRepository;
import com.pmrodrigues.vraptor.crud.converters.AbstractTypeConverter;

/**
 * Created by Marceloo on 24/09/2015.
 */
@Convert(Pessoa.class)
@RequestScoped
public class PessoaConverter extends AbstractTypeConverter<Pessoa> {
    public PessoaConverter(PessoaRepository repository) {
        super(repository);
    }
}
