package com.pmrodrigues.sisgns.repositories.impl;

import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import com.pmrodrigues.sisgns.models.security.Usuario;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import static java.lang.String.format;
import static org.hibernate.criterion.Restrictions.eq;

@Repository("UsuarioRepository")
public class UsuarioRepositoryImpl extends AbstractRepository<Usuario> implements UsuarioRepository {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger
            .getLogger(UsuarioRepositoryImpl.class);

    @Override
    public Usuario findByEmail(final String email) {

        LOGGER.debug(format("Pesquisando usuário pelo email %s", email));

        final Usuario usuario = (Usuario) super.getSession()
                .createCriteria(Usuario.class)
                .add(eq("email", email))
                .uniqueResult();

        LOGGER.debug(format("Usuario %s encontrado", usuario));

        return usuario;
    }

}
