package com.pmrodrigues.sisgns.eventlisteners;

import com.pmrodrigues.security.event.UserUpdateEvent;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Marceloo on 28/01/2016.
 */
@Component
public class UserUpdateEventListener implements com.pmrodrigues.security.event.listener.UserUpdateEventListener {

    private final UsuarioRepository repository;

    @Autowired
    public UserUpdateEventListener(final UsuarioRepository repository) {

        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(UserUpdateEvent event) {
        repository.set((Usuario) event.getSource());
    }
}
