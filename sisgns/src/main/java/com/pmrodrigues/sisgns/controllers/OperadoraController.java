package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.*;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.repositories.OperadoraRepository;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;

import static br.com.caelum.vraptor.view.Results.json;

/**
 * Created by Marceloo on 28/09/2015.
 */
@Resource
@CRUD
public class OperadoraController extends AbstractCRUDController<Operadora> {
    public OperadoraController(final OperadoraRepository repository, final Result result, final Validator validator) {
        super(repository, result, validator);
    }

    @Get
    @Path("/operadora/{nome}.json")
    public ResultList<Operadora> buscarOperadoraPeloNome(final String nome) {

        final ResultList<Operadora> resultList = this.getRepository().search(new Operadora().comNome(nome));

        this.getResult().use(json())
                .from(resultList.todos().getConsulta())
                .serialize();

        return resultList;

    }
}
