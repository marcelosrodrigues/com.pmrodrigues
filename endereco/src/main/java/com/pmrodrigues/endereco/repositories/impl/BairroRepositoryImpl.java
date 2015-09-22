package com.pmrodrigues.endereco.repositories.impl;

import com.pmrodrigues.endereco.models.Bairro;
import com.pmrodrigues.endereco.repositories.BairroRepository;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marceloo on 22/09/2015.
 */
@Repository
public class BairroRepositoryImpl extends AbstractRepository<Bairro> implements BairroRepository {
}
