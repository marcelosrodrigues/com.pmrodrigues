package com.pmrodrigues.security.event;

import com.pmrodrigues.security.models.Usuario;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Marceloo on 28/01/2016.
 */
public class UserUpdateEvent extends ApplicationEvent {

    public UserUpdateEvent(Usuario usuario) {
        super(usuario);

    }
}
