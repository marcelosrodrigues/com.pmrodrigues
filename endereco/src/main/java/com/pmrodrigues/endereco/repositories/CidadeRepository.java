package com.pmrodrigues.endereco.repositories;

import com.pmrodrigues.endereco.models.Cidade;
import com.pmrodrigues.endereco.models.Estado;
import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.persistence.daos.ResultList;

/**
 * Created by Marceloo on 22/09/2015.
 */
public interface CidadeRepository extends Repository<Cidade> {

    ResultList<Cidade> buscarCidadesDoEstadoPeloNome(final Estado estado, final String nome);
}
