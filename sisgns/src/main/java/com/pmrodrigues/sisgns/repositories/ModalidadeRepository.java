package com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Modalidade;

/**
 * Created by Marceloo on 23/11/2015.
 */
public interface ModalidadeRepository extends Repository<Modalidade> {

    ResultList<Modalidade> buscarModalidadePorNome(final String nome);
}
