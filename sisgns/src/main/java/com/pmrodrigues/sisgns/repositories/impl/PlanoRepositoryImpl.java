package com.pmrodrigues.sisgns.repositories.impl;

import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.models.Plano;
import com.pmrodrigues.sisgns.repositories.PlanoRepository;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.like;

/**
 * Created by Marceloo on 29/09/2015.
 */
@Repository
public class PlanoRepositoryImpl extends AbstractRepository<Plano> implements PlanoRepository {
    @Override
    public ResultList<Plano> buscarPlanosPeloNome(final Operadora operadora, final String plano) {
        return new ResultList<>(this.getSession().createCriteria(Plano.class)
                .add(eq("operadora", operadora))
                .add(like("nome", plano, MatchMode.START))
                .addOrder(Order.asc("nome")));
    }
}
