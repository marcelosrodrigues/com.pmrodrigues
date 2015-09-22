package com.pmrodrigues.endereco.repositories.impl;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.endereco.repositories.LogradouroRepository;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import org.springframework.stereotype.Repository;

import static org.hibernate.criterion.Restrictions.eq;

/**
 * Created by Marceloo on 22/09/2015.
 */
@Repository
public class LogradouroRepositoryImpl extends AbstractRepository<Logradouro> implements LogradouroRepository {
    @Override
    public Logradouro getByCEP(String cep) {
        return (Logradouro) this.getSession().createCriteria(Logradouro.class)
                    .add(eq("cep", cep))
                    .uniqueResult();
    }
}
