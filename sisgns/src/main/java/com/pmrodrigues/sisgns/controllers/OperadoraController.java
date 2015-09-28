package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.repositories.OperadoraRepository;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;

/**
 * Created by Marceloo on 28/09/2015.
 */
@Resource
@CRUD
public class OperadoraController extends AbstractCRUDController<Operadora> {
    protected OperadoraController(final OperadoraRepository repository, final Result result, final Validator validator) {
        super(repository, result, validator);
    }
}
