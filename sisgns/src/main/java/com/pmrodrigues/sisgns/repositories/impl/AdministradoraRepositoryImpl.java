package com.pmrodrigues.sisgns.repositories.impl;

import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.repositories.AdministradoraRepository;
import com.pmrodrigues.vraptor.crud.exceptions.UniqueException;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;
import static org.hibernate.criterion.Restrictions.*;

/**
 * Created by Marceloo on 24/09/2015.
 */
@Repository
public class AdministradoraRepositoryImpl extends AbstractRepository<Administradora> implements AdministradoraRepository {
    @Override
    public ResultList<Administradora> search(final Administradora administradora) {

        final Criteria criteria = this.getSession()
                .createCriteria(Administradora.class)
                .addOrder(Order.asc("nome"));

        if (administradora != null && (!isBlankOrNull(administradora.getNome()) || !isBlankOrNull(administradora.getNumeroDocumento()))) {
            if (!isBlankOrNull(administradora.getNome()) && !isBlankOrNull(administradora.getNumeroDocumento())) {
                criteria.add(or(
                        like("nome", administradora.getNome(), MatchMode.START),
                        like("numeroDocumento", administradora.getNumeroDocumento(), MatchMode.START)));
            } else if (!isBlankOrNull(administradora.getNumeroDocumento())) {
                criteria.add(like("numeroDocumento", administradora.getNumeroDocumento(), MatchMode.START));
            } else {
                criteria.add(like("nome", administradora.getNome(), MatchMode.START));
            }
        }

        return new ResultList<>(criteria);

    }

    @Override
    public void add(final Administradora administradora) {

        final Administradora existia = findAdministradorByCNPJ(administradora.getNumeroDocumento());

        if (existia == null) {
            super.add(administradora);
        } else {
            throw new UniqueException("Não foi possível salvar a Administradora. Já existe uma cadastrada com o mesmo CNPJ");
        }

    }

    @Override
    public Administradora findAdministradorByCNPJ(final String numeroDocumento) {
        return (Administradora) this.getSession()
                .createCriteria(Administradora.class)
                .add(eq("numeroDocumento", numeroDocumento))
                .uniqueResult();
    }

    @Override
    public void set(final Administradora administradora) {
        final Administradora existia = findAdministradorByCNPJ(administradora.getNumeroDocumento());
        if (existia == null) {
            super.set(administradora);
        } else if (existia.equals(administradora)) {
            existia.setNumeroDocumento(administradora.getNome());
            existia.setResidenciais(administradora.getResidenciais());
            existia.setConvenio(administradora.getConvenio());
            existia.setCelulares(administradora.getCelulares());
            existia.getEndereco().setNumero(administradora.getEndereco().getNumero());
            existia.getEndereco().setCep(administradora.getEndereco().getCep());
            existia.getEndereco().setCidade(administradora.getEndereco().getCidade());
            existia.getEndereco().setComplemento(administradora.getEndereco().getComplemento());
            existia.getEndereco().setBairro(administradora.getEndereco().getBairro());
            existia.getEndereco().setEstado(administradora.getEndereco().getEstado());
            existia.getEndereco().setLogradouro(administradora.getEndereco().getLogradouro());
        } else {
            throw new UniqueException("Não foi possível salvar a Administradora. Já existe uma cadastrada com o mesmo CNPJ");
        }
    }
}