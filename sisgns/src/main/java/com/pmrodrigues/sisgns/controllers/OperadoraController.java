package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.ValidationMessage;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.repositories.OperadoraRepository;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;
import org.springframework.http.HttpStatus;

import static br.com.caelum.vraptor.view.Results.http;
import static br.com.caelum.vraptor.view.Results.json;
import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

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


        if (!resultList.estaVazio()) {
            this.getResult().use(json())
                    .from(resultList.todos().getConsulta())
                    .excludeAll()
                    .include("id", "codigo", "nome")
                    .serialize();
        } else {
            getResult().use(http()).setStatusCode(HttpStatus.NO_CONTENT.value());
        }
        return resultList;

    }

    @Override
    protected void validate(final Operadora object) {
        if (object.getAdministradora().getId() == null || object.getAdministradora().getId() == 0L) {
            this.getValidator().add(new ValidationMessage("Administradora é obrigatória", "object.administradora"));
        }

        if (object.getModalidade().getId() == null || object.getModalidade().getId() == 0L) {
            this.getValidator().add(new ValidationMessage("Modalidade é obrigatória", "object.modalidade"));
        }

        if (isBlankOrNull(object.getCodigo())) {
            this.getValidator().add(new ValidationMessage("Código é obrigatório", "codigo"));
        }

        if (isBlankOrNull(object.getNome())) {
            this.getValidator().add(new ValidationMessage("Nome é obrigatório", "nome"));
        }
    }
}
