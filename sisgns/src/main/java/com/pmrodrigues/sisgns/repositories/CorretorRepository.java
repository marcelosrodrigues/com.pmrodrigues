package com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Corretor;

/**
 * Created by Marceloo on 08/10/2015.
 */
public interface CorretorRepository extends Repository<Corretor> {

    ResultList<Corretor> buscarCorretorPorNome(final String nome);
}
