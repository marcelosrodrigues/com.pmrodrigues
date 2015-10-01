package com.pmrodrigues.security;


import com.pmrodrigues.security.models.Usuario;
import com.pmrodrigues.security.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;
import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

/**
 * Created by Marceloo on 26/03/2015.
 */
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logging = Logger.getLogger(SuccessHandler.class);

    @Autowired
    private UserService service;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException, ServletException {

        final String email = (String) authentication.getPrincipal();

        logging.debug(format("acesso liberado ao sistema para o usuario %s", email));

        final Usuario usuario = (Usuario) service.loadUserByUsername(email);
        usuario.desbloquear();
        service.update(usuario);
        String context = "";
        if (!isBlankOrNull(request.getContextPath())) {
            context = request.getContextPath();
        }

        response.sendRedirect(context + "/index.do");
    }
}
