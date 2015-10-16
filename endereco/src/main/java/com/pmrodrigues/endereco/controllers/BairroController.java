package com.pmrodrigues.endereco.controllers;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import com.pmrodrigues.endereco.models.Bairro;
import com.pmrodrigues.endereco.models.Cidade;
import com.pmrodrigues.endereco.repositories.BairroRepository;
import com.pmrodrigues.persistence.daos.ResultList;
import org.springframework.http.HttpStatus;

import static br.com.caelum.vraptor.view.Results.http;
import static br.com.caelum.vraptor.view.Results.json;

/**
 * Created by Marceloo on 25/09/2015.
 */
@Resource
public class BairroController {

    private final BairroRepository repository;
    private final Result result;

    public BairroController(final BairroRepository repository, final Result result) {
        this.repository = repository;
        this.result = result;
    }

    @Get
    @Path("/endereco/bairro/{cidade}/{nome}.json")
    public ResultList<Bairro> buscarBairroDaCidadePeloNome(final Cidade cidade, final String nome) {

        if (cidade == null) {
            result.use(http())
                    .setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
            return null;
        }

        final ResultList<Bairro> bairros = this.repository.getBairrosDaCidadePeloNome(cidade, nome);

        if (!bairros.estaVazio()) {
            result.use(json())
                    .from(bairros.todos()
                            .getConsulta())
                    .excludeAll()
                    .include("id", "nome")
                    .serialize();
        } else {
            result.use(http()).setStatusCode(HttpStatus.NO_CONTENT.value());
        }
        return bairros;


    }
}
