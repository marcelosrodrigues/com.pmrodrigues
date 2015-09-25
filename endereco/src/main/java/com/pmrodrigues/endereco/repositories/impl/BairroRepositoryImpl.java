package com.pmrodrigues.endereco.repositories.impl;

import com.pmrodrigues.endereco.models.Bairro;
import com.pmrodrigues.endereco.models.Cidade;
import com.pmrodrigues.endereco.repositories.BairroRepository;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.like;

/**
 * Created by Marceloo on 22/09/2015.
 */
@Repository
public class BairroRepositoryImpl extends AbstractRepository<Bairro> implements BairroRepository {
    @Override
    public ResultList<Bairro> getBairrosDaCidadePeloNome(final Cidade cidade, final String nome) {
        return new ResultList<>(this.getSession()
                .createCriteria(Bairro.class)
                .add(eq("cidade", cidade))
                .add(like("nome", nome, MatchMode.START))
                .addOrder(Order.asc("nome")));
    }
}
