package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.*;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.repositories.AdministradoraRepository;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;

import static br.com.caelum.vraptor.view.Results.json;

/**
 * Created by Marceloo on 24/09/2015.
 */
@Resource
@CRUD
public class AdminstradoraController extends AbstractCRUDController<Administradora> {

    public AdminstradoraController(final AdministradoraRepository repository, final Result result, final Validator validator) {
        super(repository, result, validator);
    }

    @Get
    @Path("/administradora/{nome}.json")
    public ResultList<Administradora> buscarAdministradoraPeloNome(final String nome) {
        final Administradora administradora = new Administradora();
        administradora.setNome(nome);
        administradora.setNumeroDocumento(nome);
        final ResultList<Administradora> resultList = this.getRepository()
                .search(administradora);
        this.getResult().use(json())
                .from(resultList.todos()
                        .getConsulta())
                .serialize();

        return resultList;
    }
}
