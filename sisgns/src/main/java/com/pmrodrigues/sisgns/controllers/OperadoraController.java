package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.*;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.OperadoraRepository;
import com.pmrodrigues.sisgns.securities.SecurityContext;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.annotations.Insert;
import com.pmrodrigues.vraptor.crud.annotations.Update;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;
import org.springframework.http.HttpStatus;

import static br.com.caelum.vraptor.view.Results.http;
import static br.com.caelum.vraptor.view.Results.json;

/**
 * Created by Marceloo on 28/09/2015.
 */
@Resource
@CRUD
public class OperadoraController extends AbstractCRUDController<Operadora> {
    private final SecurityContext securityContext;

    public OperadoraController(final OperadoraRepository repository, SecurityContext securityContext, final Result result, final Validator validator) {
        super(repository, result, validator);
        this.securityContext = securityContext;
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

    @Insert
    public void doInsert(final Operadora object) {
        final Usuario usuarioLogado = securityContext.getUsuarioLogado();
        if (usuarioLogado.getAdministradora() != null) {
            object.setAdministradora(usuarioLogado.getAdministradora());
        }

        this.getRepository().add(object);
    }

    @Update
    public void doUpdate(final Operadora object) {
        final Usuario usuarioLogado = securityContext.getUsuarioLogado();
        final Operadora toUpdate = this.getRepository().findById(object.getId());
        toUpdate.setCodigo(object.getCodigo());
        toUpdate.setNome(object.getNome());
        toUpdate.setQualicorp(object.getQualicorp());
        toUpdate.setQuantidadeParcelas(object.getQuantidadeParcelas());
        toUpdate.setUsaPrimeiraParcela(object.getUsaPrimeiraParcela());
        toUpdate.setModalidade(object.getModalidade());
        if (usuarioLogado.getAdministradora() != null) {
            toUpdate.setAdministradora(usuarioLogado.getAdministradora());
        }

        getRepository().set(toUpdate);

    }
}
