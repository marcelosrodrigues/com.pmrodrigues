package com.pmrodrigues.endereco.repositories;

import com.pmrodrigues.endereco.models.Estado;
import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.persistence.daos.ResultList;

public interface EstadoRepository extends Repository<Estado> {

    Estado getByUF(final String uf);

    ResultList<Estado> listEstadoByNome(final String nome);

}