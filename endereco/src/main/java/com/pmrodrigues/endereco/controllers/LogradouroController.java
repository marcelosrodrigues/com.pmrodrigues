package com.pmrodrigues.endereco.controllers;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.endereco.repositories.LogradouroRepository;

import static br.com.caelum.vraptor.view.Results.json;

/**
 * Created by Marceloo on 24/09/2015.
 */
@Resource
public class LogradouroController {

    private final LogradouroRepository repository;
    private final Result result;

    public LogradouroController(final LogradouroRepository repository, final Result result) {
        this.repository = repository;
        this.result = result;
    }

    @Get
    @Path("/endereco/{cep}/logradouro.json")
    public Logradouro buscarLogradouroPorCEP(final String cep) {

        final Logradouro logradouro = repository.getByCEP(cep);
        result.use(json()).from(logradouro)
                .excludeAll()
                .include("id",
                        "logradouro",
                        "cep",
                        "bairro",
                        "bairro.id",
                        "bairro.nome",
                        "bairro.cidade",
                        "bairro.cidade.id",
                        "bairro.cidade.nome",
                        "bairro.cidade.estado",
                        "bairro.cidade.estado.id",
                        "bairro.cidade.estado.uf",
                        "bairro.cidade.estado.nome"
                )
                .serialize();

        return logradouro;

    }
}
