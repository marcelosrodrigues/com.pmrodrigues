package test.com.pmrodrigues.vraptor.crud.controller;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;

/**
 * Created by Marceloo on 14/04/2015.
 */
public class LojaController extends AbstractCRUDController<Loja> {

    protected LojaController(Repository<Loja> repository, Result result, Validator validator) {
        super(repository, result, validator);
    }
}
