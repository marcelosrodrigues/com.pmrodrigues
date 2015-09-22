package com.pmrodrigues.pessoa.repository.impl;

import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import com.pmrodrigues.pessoa.models.Pessoa;
import com.pmrodrigues.pessoa.repository.PessoaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marceloo on 22/09/2015.
 */
@Repository
public class PessoaRepositoryImpl extends AbstractRepository<Pessoa> implements PessoaRepository {
}
