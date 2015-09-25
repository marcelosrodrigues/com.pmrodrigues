package com.pmrodrigues.endereco.repositories.impl;

import com.pmrodrigues.endereco.models.Cidade;
import com.pmrodrigues.endereco.models.Estado;
import com.pmrodrigues.endereco.repositories.CidadeRepository;
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
public class CidadeRepositoryImpl extends AbstractRepository<Cidade> implements CidadeRepository {
    @Override
    public ResultList<Cidade> buscarCidadesDoEstadoPeloNome(Estado estado, String nome) {
        return new ResultList<>(this.getSession()
                .createCriteria(Cidade.class)
                .add(eq("estado", estado))
                .add(like("nome", nome, MatchMode.START))
                .addOrder(Order.asc("nome"))
        );
    }
}
