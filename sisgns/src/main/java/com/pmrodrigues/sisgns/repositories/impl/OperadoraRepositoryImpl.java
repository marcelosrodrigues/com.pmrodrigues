package com.pmrodrigues.sisgns.repositories.impl;

import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.repositories.OperadoraRepository;
import com.pmrodrigues.utilities.strings.ReflectionUtils;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 * Created by Marceloo on 28/09/2015.
 */
@Repository
public class OperadoraRepositoryImpl extends AbstractRepository<Operadora> implements OperadoraRepository {

    @Override
    public ResultList<Operadora> search(Operadora operadora) {
        ReflectionUtils.nullifyStrings(operadora);
        return new ResultList<>(this.getSession().createCriteria(Operadora.class)
                .add(Example.create(operadora)
                        .enableLike(MatchMode.START))
                .addOrder(Order.asc("nome")));
    }
}
