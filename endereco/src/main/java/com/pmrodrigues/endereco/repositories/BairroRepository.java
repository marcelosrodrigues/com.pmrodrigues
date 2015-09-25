package com.pmrodrigues.endereco.repositories;

import com.pmrodrigues.endereco.models.Bairro;
import com.pmrodrigues.endereco.models.Cidade;
import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.persistence.daos.ResultList;

/**
 * Created by Marceloo on 22/09/2015.
 */
public interface BairroRepository extends Repository<Bairro> {

    ResultList<Bairro> getBairrosDaCidadePeloNome(final Cidade cidade, final String nome);
}
