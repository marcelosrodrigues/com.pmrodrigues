package com.pmrodrigues.security.models;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Marceloo on 15/04/2015.
 */
public interface Usuario extends UserDetails {

    void realizouTentativaInvalida();

    void desbloquear();
}
