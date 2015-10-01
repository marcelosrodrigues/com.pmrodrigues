package com.pmrodrigues.security.services;

import com.pmrodrigues.security.models.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Marceloo on 15/04/2015.
 */
public interface UserService extends UserDetailsService {
    void update(final Usuario usuario);
}
