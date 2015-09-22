package test.com.pmrodrigues.taglib;

import br.com.caelum.vraptor.validator.ValidationMessage;
import com.pmrodrigues.taglib.InputDateTag;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.springframework.mock.web.MockJspWriter;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.PageContext;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestInputDateTag {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    private JspContext jspContext = context.mock(JspContext.class);
    private StringWriter writer = new StringWriter();

    @Test
    public void doTagWithOutError() throws Exception {

        context.checking(new Expectations() {{
            allowing(jspContext).getOut();
            will(returnValue(new MockJspWriter(writer)));

            allowing(jspContext).getAttribute("errors", PageContext.REQUEST_SCOPE);
            will(returnValue(null));
        }});


        final InputDateTag textview = new InputDateTag();
        textview.setJspContext(jspContext);
        String EXPECTED = "<div class=\"form-group \"><label for=\"object.nome\" class=\"control-label\">Nome</label><input type=\"date\" value=\"teste\" id=\"object.nome\" name=\"object.nome\" class=\"form-control\" placeholder=\"Nome\" /></div>";

        textview.setId("object.nome");
        textview.setLabel("Nome");
        textview.setValue("teste");

        textview.doTag();

        assertEquals(EXPECTED, writer.toString());

    }

    @Test
    public void doTagRemovingLabel() throws Exception {

        context.checking(new Expectations() {{
            allowing(jspContext).getOut();
            will(returnValue(new MockJspWriter(writer)));

            allowing(jspContext).getAttribute("errors", PageContext.REQUEST_SCOPE);
            will(returnValue(null));
        }});


        final InputDateTag textview = new InputDateTag();
        textview.setJspContext(jspContext);
        String EXPECTED = "<div class=\"form-group \"><input type=\"date\" value=\"teste\" id=\"object.nome\" name=\"object.nome\" class=\"form-control\" placeholder=\"Nome\" /></div>";

        textview.setId("object.nome");
        textview.setLabel("Nome");
        textview.setValue("teste");
        textview.setShowLabel(false);

        textview.doTag();

        assertEquals(EXPECTED, writer.toString());

    }

    @Test
    public void doTagWithPattern() throws Exception {

        context.checking(new Expectations() {{
            allowing(jspContext).getOut();
            will(returnValue(new MockJspWriter(writer)));

            allowing(jspContext).getAttribute("errors", PageContext.REQUEST_SCOPE);
            will(returnValue(null));
        }});


        final InputDateTag textview = new InputDateTag();
        textview.setJspContext(jspContext);
        String EXPECTED = "<div class=\"form-group \"><label for=\"object.nome\" class=\"control-label\">Nome</label><input type=\"date\" value=\"teste\" id=\"object.nome\" name=\"object.nome\" class=\"form-control\" placeholder=\"Nome\" pattern=\"dd/MM/yyyy\"/></div>";

        textview.setId("object.nome");
        textview.setLabel("Nome");
        textview.setValue("teste");
        textview.setShowLabel(true);
        textview.setPattern("dd/MM/yyyy");

        textview.doTag();

        assertEquals(EXPECTED, writer.toString());

    }

    @Test
    public void doTagWithError() throws Exception {

        final ValidationMessage error = context.mock(ValidationMessage.class);
        final List<ValidationMessage> errors = new ArrayList<>();
        errors.add(error);

        context.checking(new Expectations() {{
            allowing(jspContext).getOut();
            will(returnValue(new MockJspWriter(writer)));

            allowing(jspContext).getAttribute("errors", PageContext.REQUEST_SCOPE);
            will(returnValue(errors));

            oneOf(error).getCategory();
            will(returnValue("nome"));

        }});


        final InputDateTag textview = new InputDateTag();
        textview.setJspContext(jspContext);
        String EXPECTED = "<div class=\"form-group has-error\"><label for=\"object.nome\" class=\"control-label\">Nome</label><input type=\"date\" value=\"\" id=\"object.nome\" name=\"object.nome\" class=\"form-control\" placeholder=\"Nome\" /></div>";

        textview.setId("object.nome");
        textview.setLabel("Nome");
        textview.setErrorField("nome");

        textview.doTag();

        assertEquals(EXPECTED, writer.toString());
    }
}