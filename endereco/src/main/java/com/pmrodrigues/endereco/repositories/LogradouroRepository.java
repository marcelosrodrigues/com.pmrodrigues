package com.pmrodrigues.endereco.repositories;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.persistence.daos.Repository;

/**
 * Created by Marceloo on 22/09/2015.
 */
public interface LogradouroRepository extends Repository<Logradouro> {

    Logradouro getByCEP(final String cep);
}
