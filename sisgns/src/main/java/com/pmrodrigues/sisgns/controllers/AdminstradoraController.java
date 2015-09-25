package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.repositories.AdministradoraRepository;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;

/**
 * Created by Marceloo on 24/09/2015.
 */
@Resource
@CRUD
public class AdminstradoraController extends AbstractCRUDController<Administradora> {

    public AdminstradoraController(final AdministradoraRepository repository, final Result result, final Validator validator) {
        super(repository, result, validator);
    }

}
