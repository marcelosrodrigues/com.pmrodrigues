package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import com.pmrodrigues.sisgns.models.Corretor;
import com.pmrodrigues.sisgns.repositories.CorretorRepository;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;

/**
 * Created by Marceloo on 08/10/2015.
 */
@CRUD
@Resource
public class CorretorController extends AbstractCRUDController<Corretor> {
    protected CorretorController(final CorretorRepository repository, final Result result, final Validator validator) {
        super(repository, result, validator);
    }
}
