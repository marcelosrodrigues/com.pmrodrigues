package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.*;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.ModalidadeRepository;
import com.pmrodrigues.sisgns.securities.SecurityContext;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.annotations.Insert;
import com.pmrodrigues.vraptor.crud.annotations.Update;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;

import static br.com.caelum.vraptor.view.Results.json;

/**
 * Created by Marceloo on 23/11/2015.
 */
@Resource
@CRUD
public class ModalidadeController extends AbstractCRUDController<Modalidade> {

    private final SecurityContext securityContext;

    public ModalidadeController(ModalidadeRepository repository, SecurityContext securityContext, Result result, Validator validator) {
        super(repository, result, validator);
        this.securityContext = securityContext;
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

    @Insert
    public void doInsert(final Modalidade object) {

        final Usuario usuarioLogado = securityContext.getUsuarioLogado();
        if (usuarioLogado.getAdministradora() != null) {
            object.setAdministradora(usuarioLogado.getAdministradora());
        }

        this.getRepository().add(object);

    }

    @Update
    public void doUpdate(Modalidade object) {
        final Usuario usuarioLogado = securityContext.getUsuarioLogado();
        final Modalidade toUpdate = getRepository().findById(object.getId());
        toUpdate.setCodigo(object.getCodigo());
        toUpdate.setNome(object.getNome());
        if (usuarioLogado.getAdministradora() != null) {
            toUpdate.setAdministradora(usuarioLogado.getAdministradora());
        }

        getRepository().set(toUpdate);
    }
}
