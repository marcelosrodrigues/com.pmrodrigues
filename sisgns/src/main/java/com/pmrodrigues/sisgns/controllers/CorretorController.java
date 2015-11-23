package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.*;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Corretor;
import com.pmrodrigues.sisgns.repositories.CorretorRepository;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;

import static br.com.caelum.vraptor.view.Results.json;

/**
 * Created by Marceloo on 08/10/2015.
 */
@CRUD
@Resource
public class CorretorController extends AbstractCRUDController<Corretor> {
    protected CorretorController(final CorretorRepository repository, final Result result, final Validator validator) {
        super(repository, result, validator);
    }

    @Get
    @Path("/corretor/{nome}.json")
    public ResultList<Corretor> buscarCorretorPorNome(final String nome) {

        ResultList<Corretor> resultList = ((CorretorRepository) this.getRepository())
                .buscarCorretorPorNome(nome);
        this.getResult().use(json())
                .from(resultList.todos().getConsulta())
                .excludeAll()
                .include("id", "nome")
                .serialize();

        return resultList;

    }
}
