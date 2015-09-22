package com.pmrodrigues.endereco.repositories.impl;

import com.pmrodrigues.endereco.models.Cidade;
import com.pmrodrigues.endereco.repositories.CidadeRepository;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marceloo on 22/09/2015.
 */
@Repository
public class CidadeRepositoryImpl extends AbstractRepository<Cidade> implements CidadeRepository {
}
