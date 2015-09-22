package com.pmrodrigues.endereco.repositories.impl;

import com.pmrodrigues.endereco.models.Estado;
import com.pmrodrigues.endereco.repositories.EstadoRepository;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import org.springframework.stereotype.Repository;

import static org.hibernate.criterion.Restrictions.eq;

@Repository("EstadoRepository")
public class EstadoRepositoryImpl extends AbstractRepository<Estado> implements EstadoRepository {

    private static final long serialVersionUID = 1L;

    @Override
    public Estado getByUF(final String uf) {
        return (Estado) this.getSession().createCriteria(Estado.class)
                .add(eq("uf", uf))
                .uniqueResult();
    }
}
