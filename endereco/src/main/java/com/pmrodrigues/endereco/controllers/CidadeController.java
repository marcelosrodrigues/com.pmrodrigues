package com.pmrodrigues.endereco.controllers;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import com.pmrodrigues.endereco.models.Cidade;
import com.pmrodrigues.endereco.models.Estado;
import com.pmrodrigues.endereco.repositories.CidadeRepository;
import com.pmrodrigues.persistence.daos.ResultList;

import static br.com.caelum.vraptor.view.Results.json;

/**
 * Created by Marceloo on 25/09/2015.
 */
@Resource
public class CidadeController {
    private final CidadeRepository repository;
    private final Result result;

    public CidadeController(final CidadeRepository repository, final Result result) {
        this.repository = repository;
        this.result = result;
    }

    @Get
    @Path("/endereco/cidade/{estado}/{nome}.json")
    public ResultList<Cidade> buscarCidade(final Estado estado, final String nome) {

        final ResultList<Cidade> resultList = repository.buscarCidadesDoEstadoPeloNome(estado, nome);
        result.use(json()).from(resultList
                .todos()
                .getConsulta())
                .excludeAll()
                .include("id", "nome")
                .serialize();

        return resultList;

    }
}
