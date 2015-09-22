package com.pmrodrigues.sisgns.services;

import com.pmrodrigues.security.services.UserService;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service("userService")
public class UsuarioService implements UserService {

    private static final Logger logging = Logger.getLogger(UsuarioService.class);
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        logging.debug(format("Pesquisando usuario com email %s", username));

        final Usuario usuario = repository.findByEmail(username);
        if (usuario != null) {
            return usuario;
        }

        throw new UsernameNotFoundException(format("Usuario %s não encontrado ou senha invalida", username));
    }
}
