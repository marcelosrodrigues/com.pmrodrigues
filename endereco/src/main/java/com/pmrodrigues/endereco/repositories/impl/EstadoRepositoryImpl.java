package com.pmrodrigues.endereco.repositories.impl;

import com.pmrodrigues.endereco.models.Estado;
import com.pmrodrigues.endereco.repositories.EstadoRepository;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import static org.hibernate.criterion.Restrictions.*;

@Repository
public class EstadoRepositoryImpl extends AbstractRepository<Estado> implements EstadoRepository {

    private static final long serialVersionUID = 1L;

    @Override
    public Estado getByUF(final String uf) {
        return (Estado) this.getSession().createCriteria(Estado.class)
                .add(eq("uf", uf))
                .uniqueResult();
    }

    @Override
    public ResultList<Estado> listEstadoByNome(final String nome) {
        return new ResultList<>(this.getSession()
                .createCriteria(Estado.class)
                .add(or(like("uf", nome, MatchMode.START), like("nome", nome, MatchMode.START)))
                .addOrder(Order.asc("nome")));
    }
}
