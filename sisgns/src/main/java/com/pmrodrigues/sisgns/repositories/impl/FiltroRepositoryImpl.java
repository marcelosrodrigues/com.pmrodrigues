package com.pmrodrigues.sisgns.repositories.impl;

import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import com.pmrodrigues.sisgns.models.security.Filtro;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.FiltroRepository;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import static org.hibernate.criterion.Restrictions.eq;

/**
 * Created by Marceloo on 11/12/2015.
 */
@Repository
public class FiltroRepositoryImpl extends AbstractRepository<Filtro> implements FiltroRepository {
    @Override
    public ResultList<Filtro> buscarFiltrosHabilitadosParaOUsuario(final Usuario usuario) {
        return new ResultList<>(this.getSession().createCriteria(Filtro.class)
                .createAlias("perfil", "perfil", JoinType.INNER_JOIN)
                .createAlias("perfil.usuarios", "usuario", JoinType.INNER_JOIN)
                .add(eq("usuario.id", usuario.getId())));
    }


}
