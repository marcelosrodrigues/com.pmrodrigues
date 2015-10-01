package com.pmrodrigues.sisgns.repositories.impl;

import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.repositories.AdministradoraRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;
import static org.hibernate.criterion.Restrictions.like;
import static org.hibernate.criterion.Restrictions.or;

/**
 * Created by Marceloo on 24/09/2015.
 */
@Repository
public class AdministradoraRepositoryImpl extends AbstractRepository<Administradora> implements AdministradoraRepository {
    @Override
    public ResultList<Administradora> search(final Administradora administradora) {

        final Criteria criteria = this.getSession().createCriteria(Administradora.class).addOrder(Order.asc("nome"));

        if (!isBlankOrNull(administradora.getNome()) && !isBlankOrNull(administradora.getNumeroDocumento())) {
            criteria.add(or(
                    like("nome", administradora.getNome(), MatchMode.START),
                    like("numeroDocumento", administradora.getNumeroDocumento(), MatchMode.START)));
        }

        return new ResultList<>(criteria);


    }
}