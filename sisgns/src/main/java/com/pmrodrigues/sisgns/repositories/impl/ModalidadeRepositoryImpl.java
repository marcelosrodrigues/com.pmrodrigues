package com.pmrodrigues.sisgns.repositories.impl;

import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import com.pmrodrigues.sisgns.models.Modalidade;
import com.pmrodrigues.sisgns.repositories.ModalidadeRepository;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import static org.hibernate.criterion.Restrictions.like;
import static org.hibernate.criterion.Restrictions.or;

/**
 * Created by Marceloo on 23/11/2015.
 */
@Repository
public class ModalidadeRepositoryImpl extends AbstractRepository<Modalidade> implements ModalidadeRepository {
    @Override
    public ResultList<Modalidade> buscarModalidadePorNome(final String nome) {
        return new ResultList<>(this.getSession()
                .createCriteria(Modalidade.class)
                .add(or(like("nome", nome, MatchMode.START),
                        like("codigo", nome, MatchMode.START)))
                .addOrder(Order.asc("nome")));
    }
}
