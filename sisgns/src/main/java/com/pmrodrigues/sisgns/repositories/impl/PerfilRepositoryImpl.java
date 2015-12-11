package com.pmrodrigues.sisgns.repositories.impl;

import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import com.pmrodrigues.sisgns.models.security.Perfil;
import com.pmrodrigues.sisgns.repositories.PerfilRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marceloo on 11/12/2015.
 */
@Repository
public class PerfilRepositoryImpl extends AbstractRepository<Perfil> implements PerfilRepository {
}
