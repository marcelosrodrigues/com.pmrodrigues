package test.com.pmrodrigues.taglib;

import br.com.caelum.vraptor.validator.ValidationMessage;
import com.pmrodrigues.taglib.CheckboxListTag;
import com.pmrodrigues.taglib.RadioListTag;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.springframework.mock.web.MockJspWriter;
import test.com.pmrodrigues.taglib.bean.Secao;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 23/01/2015.
 */
public class TestRadioListTag {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    private JspContext jspContext = context.mock(JspContext.class);
    private StringWriter writer = new StringWriter();


    @Test
    public void doTag() throws Exception {

        Collection<Secao> secoes = new ArrayList<>();
        Secao secao = new Secao();
        secao.setId(1L);
        secao.setNome("TESTE");

        secoes.add(secao);
        context.checking(new Expectations() {{
            allowing(jspContext).getOut();
            will(returnValue(new MockJspWriter(writer)));

            allowing(jspContext).getAttribute("errors", PageContext.REQUEST_SCOPE);
            will(returnValue(null));
        }});

        RadioListTag radio = new RadioListTag();
        radio.setJspContext(jspContext);
        radio.setId("teste");
        radio.setLabel("teste");
        radio.setValue(secoes);
        radio.setLabelField("nome");
        radio.setValueField("id");
        radio.doTag();


        final String EXPECTED = "<div class=\"form-group \"><label for=\"teste\">teste </label>&nbsp;<label class=\"radio-inline\"><input type=\"radio\" name=\"teste\" id=\"teste\" value=\"1\" >TESTE</label></div>";
        assertEquals(EXPECTED, writer.toString());

    }

    @Test
    public void doTagChecked() throws IOException, JspException {
        Collection<Secao> secoes = new ArrayList<>();
        Secao secao = new Secao();
        secao.setId(1L);
        secao.setNome("TESTE");

        secoes.add(secao);
        context.checking(new Expectations() {{
            allowing(jspContext).getOut();
            will(returnValue(new MockJspWriter(writer)));

            allowing(jspContext).getAttribute("errors", PageContext.REQUEST_SCOPE);
            will(returnValue(null));
        }});

        RadioListTag radio = new RadioListTag();
        radio.setJspContext(jspContext);
        radio.setId("teste");
        radio.setLabel("teste");
        radio.setValue(secoes);
        radio.setChecked(secoes);
        radio.setLabelField("nome");
        radio.setValueField("id");
        radio.doTag();


        final String EXPECTED = "<div class=\"form-group \"><label for=\"teste\">teste </label>&nbsp;<label class=\"radio-inline\"><input type=\"radio\" name=\"teste\" id=\"teste\" value=\"1\" checked=\"checked\">TESTE</label></div>";
        assertEquals(EXPECTED, writer.toString());
    }

    @Test
    public void doTagWithError() throws IOException, JspException {

        Collection<Secao> secoes = new ArrayList<>();
        final ValidationMessage error = context.mock(ValidationMessage.class);
        Secao secao = new Secao();
        secao.setId(1L);
        secao.setNome("TESTE");

        secoes.add(secao);
        final List<ValidationMessage> errors = new ArrayList<>();
        errors.add(error);

        context.checking(new Expectations() {{
            allowing(jspContext).getOut();
            will(returnValue(new MockJspWriter(writer)));

            allowing(jspContext).getAttribute("errors", PageContext.REQUEST_SCOPE);
            will(returnValue(errors));

            oneOf(error).getCategory();
            will(returnValue("teste"));

        }});

        CheckboxListTag checkboxlist = new CheckboxListTag();
        checkboxlist.setJspContext(jspContext);
        checkboxlist.setId("teste");
        checkboxlist.setLabel("teste");
        checkboxlist.setValue(secoes);
        checkboxlist.setLabelField("nome");
        checkboxlist.setValueField("id");
        checkboxlist.setErrorField("teste");
        checkboxlist.doTag();


        final String EXPECTED = "<div class=\"form-group has-error\"><label for=\"teste\">teste </label>&nbsp;<label class=\"checkbox-inline\"><input type=\"checkbox\" name=\"teste\" id=\"teste\" value=\"1\" >TESTE</label></div>";
        assertEquals(EXPECTED, writer.toString());

    }
}
