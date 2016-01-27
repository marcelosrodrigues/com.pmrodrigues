package test.com.pmrodrigues.sisgns.securities.taglib;

import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import com.pmrodrigues.sisgns.securities.taglib.AdministradoraAutoCompleteTag;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockJspWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import java.io.IOException;
import java.io.StringWriter;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;
import static org.junit.Assert.*;

/**
 * Created by Marceloo on 27/01/2016.
 */
public class TestAdministradoraAutoCompleteTag {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    private Usuario usuario = context.mock(Usuario.class);
    private UsuarioRepository repository = context.mock(UsuarioRepository.class);
    private HttpServletRequest request = context.mock(HttpServletRequest.class);
    private ServletContext servletContext = context.mock(ServletContext.class);
    private PageContext jspContext = context.mock(PageContext.class);
    private StringWriter writer = new StringWriter();
    private JspFragment fragment = context.mock(JspFragment.class);

    @Before
    public void setup() {

        context.checking(new Expectations() {{
            oneOf(jspContext).getOut();
            will(returnValue(new MockJspWriter(writer)));

            oneOf(jspContext).getRequest();
            will(returnValue(request));

            oneOf(request).getServletContext();
            will(returnValue(servletContext));

            oneOf(request).getRemoteUser();
            will(returnValue("teste@teste.com"));

            oneOf(repository).findByEmail(with(aNonNull(String.class)));
            will(returnValue(usuario));


        }});

    }

    @Test
    public void deveGerarAutocomplete() throws IOException, JspException {

        AdministradoraAutoCompleteTag tag = new AdministradoraAutoCompleteTag();
        tag.setJspContext(jspContext);
        tag.setJspBody(fragment);
        tag.setRepository(repository);
        tag.setId("object.administradora");
        tag.setValue(1L);

        context.checking(new Expectations() {{
            oneOf(usuario).getAdministradora();
            will(returnValue(null));

            oneOf(fragment).invoke(with(aNonNull(JspWriter.class)));
        }});

        tag.doTag();
        final String htmlgerado = writer.toString();
        assertNotNull(htmlgerado);
        assertFalse(isBlankOrNull(htmlgerado));
        assertEquals("<input type=\"hidden\" name=\"object.administradora\" id=\"object.administradora\" value=\"1\"/>", htmlgerado);

    }

    @Test
    public void naoDeveGerarAutocomplete() throws IOException, JspException {
        AdministradoraAutoCompleteTag tag = new AdministradoraAutoCompleteTag();
        tag.setJspContext(jspContext);
        tag.setRepository(repository);
        tag.setId("object.administradora");

        final Administradora administradora = context.mock(Administradora.class);

        context.checking(new Expectations() {{
            oneOf(usuario).getAdministradora();
            will(returnValue(administradora));

            oneOf(administradora).getId();
            will(returnValue(1L));
        }});

        tag.doTag();
        final String htmlgerado = writer.toString();
        assertNotNull(htmlgerado);
        assertFalse(isBlankOrNull(htmlgerado));
        assertEquals("<input type=\"hidden\" name=\"object.administradora\" id=\"object.administradora\" value=\"1\"/>", htmlgerado);
    }

}