package com.pmrodrigues.sisgns.repositories.impl;

import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import com.pmrodrigues.sisgns.models.Corretor;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.CorretorRepository;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import com.pmrodrigues.vraptor.crud.exceptions.UniqueException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;
import static org.hibernate.criterion.Restrictions.like;

/**
 * Created by Marceloo on 08/10/2015.
 */
@Repository
public class CorretorRepositoryImpl extends AbstractRepository<Corretor> implements CorretorRepository {

    @Autowired
    private UsuarioRepository userRepository;


    @Override
    public void add(final Corretor corretor) {
        Usuario usuario = userRepository.findByEmail(corretor.getEmail());
        if (usuario == null) {
            super.add(corretor);
        } else {
            throw new UniqueException("Não foi possível salvar o corretor pois já existe um cadastrado com o email informado");
        }
    }

    @Override
    public void set(final Corretor corretor) {

        Usuario usuario = userRepository.findByEmail(corretor.getEmail());
        if (usuario == null || usuario.equals(corretor)) {
            Corretor existente = this.findById(corretor.getId());
            existente.setNome(corretor.getNome());
            existente.setBloqueado(corretor.isBloqueado());
            existente.setEmail(corretor.getEmail());
            if (!isBlankOrNull(corretor.getPassword())) {
                existente.setPassword(corretor.getPassword());
            }

            super.set(existente);

        } else {
            throw new UniqueException("Não foi possível salvar o corretor pois já existe um cadastrado com o email informado");
        }

    }

    @Override
    public ResultList<Corretor> buscarCorretorPorNome(final String nome) {
        return new ResultList<>(this.getSession().createCriteria(Corretor.class)
                .add(like("nome", nome, MatchMode.START))
                .addOrder(Order.asc("nome")));
    }
}
