package test.com.pmrodrigues.taglib;

import com.pmrodrigues.taglib.TextAreaTag;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.springframework.mock.web.MockJspWriter;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.PageContext;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 14/04/2015.
 */
public class TestTextAreaTag {

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


        final TextAreaTag textview = new TextAreaTag();
        textview.setJspContext(jspContext);
        String EXPECTED = "<div class=\"form-group \"><label for=\"object.nome\" class=\"control-label\">Nome</label><textarea id=\"object.nome\" name=\"object.nome\" class=\"form-control\" rows=\"5\">teste</textarea></div>";

        textview.setId("object.nome");
        textview.setLabel("Nome");
        textview.setValue("teste");
        textview.setLinhas(5);

        textview.doTag();

        assertEquals(EXPECTED, writer.toString());

    }
}
