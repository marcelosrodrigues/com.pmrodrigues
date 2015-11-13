package com.pmrodrigues.sisgns.converters;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.ioc.RequestScoped;
import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.sisgns.models.FaixaEtaria;
import com.pmrodrigues.vraptor.crud.converters.AbstractTypeConverter;

/**
 * Created by Marceloo on 28/10/2015.
 */
@Convert(FaixaEtaria.class)
@RequestScoped
public class FaixaEtariaConverter extends AbstractTypeConverter<FaixaEtaria> {
    public FaixaEtariaConverter(Repository<FaixaEtaria> repository) {
        super(repository);
    }
}
