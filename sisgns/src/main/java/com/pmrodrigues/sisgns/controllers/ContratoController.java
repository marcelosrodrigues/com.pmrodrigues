package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.*;
import com.pmrodrigues.pessoa.models.PessoaFisica;
import com.pmrodrigues.sisgns.dto.Pessoa;
import com.pmrodrigues.sisgns.models.Contrato;
import com.pmrodrigues.sisgns.repositories.ContratoRepository;
import com.pmrodrigues.vraptor.crud.annotations.Tiles;

import java.util.Collection;

/**
 * Created by Marceloo on 13/11/2015.
 */
@Resource()
public class ContratoController {

    private final ContratoRepository repository;
    private final Result result;
    private final Validator validator;

    public ContratoController(final ContratoRepository repository, final Result result, final Validator validator) {

        this.repository = repository;
        this.result = result;
        this.validator = validator;
    }

    @Get
    @Path("/contrato/novo.do")
    @Tiles(definition = "formulario-template")
    public void formulario() {
        //NOPMD
    }

    @Post
    @Path("/contrato/salvar.do")
    @Tiles(definition = "index-template")
    public void salvar(final Contrato object, final Pessoa pessoa, final Collection<PessoaFisica> beneficiarios) {
        object.paraCliente(pessoa.novoEntity());
    }
}
