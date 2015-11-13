package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import com.pmrodrigues.sisgns.Constante;
import com.pmrodrigues.sisgns.models.Plano;
import com.pmrodrigues.sisgns.repositories.FaixaEtariaRepository;
import com.pmrodrigues.sisgns.repositories.PlanoRepository;
import com.pmrodrigues.vraptor.crud.annotations.Before;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.annotations.Update;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;

/**
 * Created by Marceloo on 29/09/2015.
 */
@Resource
@CRUD
public class PlanoController extends AbstractCRUDController<Plano> {
    private final FaixaEtariaRepository faixaEtariaRepository;

    public PlanoController(PlanoRepository repository, FaixaEtariaRepository faixaEtariaRepository, Result result, Validator validator) {
        super(repository, result, validator);
        this.faixaEtariaRepository = faixaEtariaRepository;
    }

    @Update
    public void doUpdate(final Plano plano) {

        final Plano toUpdate = this.getRepository().findById(plano.getId());
        toUpdate.setNome(plano.getNome());
        toUpdate.setAdministradora(plano.getAdministradora());
        toUpdate.setOperadora(plano.getOperadora());
        toUpdate.setRegra(plano.getRegra());
        toUpdate.setPrecos(plano.getPrecos());

        this.getRepository().set(toUpdate);
    }

    @Before
    public void doBefore() {
        this.getResult().include(Constante.FAIXA_ETARIA, this.faixaEtariaRepository.all());
    }
}
