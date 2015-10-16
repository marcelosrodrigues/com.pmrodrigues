package com.pmrodrigues.endereco.controllers;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import com.pmrodrigues.endereco.models.Estado;
import com.pmrodrigues.endereco.repositories.EstadoRepository;
import com.pmrodrigues.persistence.daos.ResultList;
import org.springframework.http.HttpStatus;

import static br.com.caelum.vraptor.view.Results.http;
import static br.com.caelum.vraptor.view.Results.json;

/**
 * Created by Marceloo on 25/09/2015.
 */
@Resource
public class EstadoController {

    private final EstadoRepository repository;
    private final Result result;

    public EstadoController(final EstadoRepository repository, final Result result) {
        this.repository = repository;
        this.result = result;
    }

    @Get
    @Path("/endereco/estado/{nome}.json")
    public ResultList<Estado> buscarEstadosPeloNome(final String nome) {
        ResultList<Estado> estados = repository.listEstadoByNome(nome);

        if (!estados.estaVazio()) {
            result.use(json())
                    .from(estados.todos().getConsulta())
                    .serialize();

            return estados;
        } else {
            result.use(http()).setStatusCode(HttpStatus.NO_CONTENT.value());
            return null;
        }
    }
}
