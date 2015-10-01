package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import com.pmrodrigues.sisgns.models.Plano;
import com.pmrodrigues.sisgns.repositories.PlanoRepository;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;

/**
 * Created by Marceloo on 29/09/2015.
 */
@Resource
@CRUD
public class PlanoController extends AbstractCRUDController<Plano> {
    protected PlanoController(PlanoRepository repository, Result result, Validator validator) {
        super(repository, result, validator);
    }
}
