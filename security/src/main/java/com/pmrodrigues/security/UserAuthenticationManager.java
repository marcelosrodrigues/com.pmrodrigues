package com.pmrodrigues.security;


import com.pmrodrigues.security.event.UserUpdateEvent;
import com.pmrodrigues.security.models.Usuario;
import com.pmrodrigues.security.services.UserService;
import com.pmrodrigues.security.utilities.MD5;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * Created by Marceloo on 24/03/2015.
 */
@Service
public class UserAuthenticationManager implements AuthenticationManager {

    private static final Logger logging = Logger.getLogger(UserAuthenticationManager.class);

    private final UserService service;

    private final ApplicationEventPublisher publisher;

    @Autowired
    public UserAuthenticationManager(final UserService service, final ApplicationEventPublisher publisher) {

        this.service = service;
        this.publisher = publisher;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        logging.debug(format("iniciando a autenticacao do usuario %s", authentication.getPrincipal()));

        final Usuario user = (Usuario) service.loadUserByUsername((String) authentication.getPrincipal());

        if (user.isAccountNonLocked()) {
            String password = (String) authentication.getCredentials();
            password = MD5.encrypt(password);
            if (password.equals(user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
            } else {
                user.realizouTentativaInvalida();
                publisher.publishEvent(new UserUpdateEvent(user));
                throw new BadCredentialsException(format("Usuario %s não encontrado ou senha invalida", authentication.getPrincipal()));
            }
        } else {
            throw new LockedException("Seu acesso foi bloqueado. Favor encontrar em contato com o administrador do sistema");
        }
    }
}
