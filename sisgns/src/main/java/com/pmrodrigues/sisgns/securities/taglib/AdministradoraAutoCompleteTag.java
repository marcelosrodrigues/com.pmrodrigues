package com.pmrodrigues.sisgns.securities.taglib;

import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static java.lang.String.format;

/**
 * Created by Marceloo on 27/01/2016.
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AdministradoraAutoCompleteTag extends SimpleTagSupport {

    @Setter
    private UsuarioRepository repository;

    @Setter
    private String id;

    @Setter
    private Long value;

    protected UsuarioRepository getRepository(final ServletContext servletContext) {

        if (repository == null) {
            return (UsuarioRepository) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("UsuarioRepository");
        } else {
            return repository;
        }

    }

    @Override
    public void doTag() throws JspException, IOException {
        final PageContext context = (PageContext) getJspContext();
        final JspWriter out = context.getOut();
        final HttpServletRequest request = (HttpServletRequest) context.getRequest();

        final UsuarioRepository repository = this.getRepository(request.getServletContext());
        final Usuario usuario = repository.findByEmail(request.getRemoteUser());
        final Administradora administradora = usuario.getAdministradora();
        if (administradora != null) {

            out.write(format("<input type=\"hidden\" name=\"%s\" id=\"%s\" value=\"%s\"/>",
                    this.id,
                    this.id,
                    administradora.getId()));

        } else {

            out.write(format("<input type=\"hidden\" name=\"%s\" id=\"%s\" value=\"%s\"/>",
                    this.id,
                    this.id,
                    this.value));


            getJspBody().invoke(out);
        }

    }
}
