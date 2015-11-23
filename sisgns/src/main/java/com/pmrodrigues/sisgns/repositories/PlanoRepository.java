package com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.models.Plano;

/**
 * Created by Marceloo on 29/09/2015.
 */
public interface PlanoRepository extends Repository<Plano> {
    ResultList<Plano> buscarPlanosPeloNome(final Operadora operadora, final String plano);
}
