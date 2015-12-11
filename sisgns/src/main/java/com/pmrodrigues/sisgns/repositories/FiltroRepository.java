package com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.security.Filtro;
import com.pmrodrigues.sisgns.models.security.Usuario;

/**
 * Created by Marceloo on 11/12/2015.
 */
public interface FiltroRepository extends Repository<Filtro> {
    ResultList<Filtro> buscarFiltrosHabilitadosParaOUsuario(final Usuario usuario);
}
