package com.pmrodrigues.sisgns.securities;

import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Marceloo on 01/02/2016.
 */
@Service
public class SecurityContext {

    @Autowired
    private UsuarioRepository repository;

    public Usuario getUsuarioLogado() {
        final String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findByEmail(email);
    }
}
