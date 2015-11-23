package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.*;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.repositories.ModalidadeRepository;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;

import static br.com.caelum.vraptor.view.Results.json;

/**
 * Created by Marceloo on 23/11/2015.
 */
@Resource
@CRUD
public class ModalidadeController extends AbstractCRUDController<Modalidade> {

    public ModalidadeController(ModalidadeRepository repository, Result result, Validator validator) {
        super(repository, result, validator);
    }

    @Get
    @Path("/modalidade/{nome}.json")
    public ResultList<Modalidade> buscarModalidePorNome(final String nome) {

        final ResultList<Modalidade> resultlist = ((ModalidadeRepository) this.getRepository())
                .buscarModalidadePorNome(nome);
        this.getResult().use(json())
                .from(resultlist.todos().getConsulta())
                .excludeAll()
                .include("id", "nome")
                .serialize();

        return resultlist;

    }
}
