package com.pmrodrigues.sisgns.converters;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.ioc.RequestScoped;
import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.vraptor.crud.converters.AbstractTypeConverter;

/**
 * Created by Marceloo on 28/09/2015.
 */
@Convert(Administradora.class)
@RequestScoped
public class AdministradoraConverter extends AbstractTypeConverter<Administradora> {
    public AdministradoraConverter(Repository<Administradora> repository) {
        super(repository);
    }
}
