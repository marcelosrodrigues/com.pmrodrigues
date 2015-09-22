package com.pmrodrigues.taglib;

import br.com.caelum.vraptor.validator.ValidationMessage;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

/**
 * Created by Marceloo on 16/01/2015.
 */
public class SummaryTag extends SimpleTagSupport {

    private String text;

    @Override
    public void doTag() throws JspException, IOException {

        final JspWriter out = getJspContext().getOut();
        final List<ValidationMessage> errors = (List<ValidationMessage>) getJspContext().getAttribute("errors", PageContext.REQUEST_SCOPE);
        final String sucesso = (String) getJspContext().getAttribute("sucesso", PageContext.REQUEST_SCOPE);

        if (errors != null && !errors.isEmpty()) {
            addErrorSummary(out, errors);
        } else if (sucesso != null && !"".equalsIgnoreCase(sucesso)) {
            out.print("<div class=\"alert alert-dismissable alert-success\">");
            out.print("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>");
            out.print("<strong>" + sucesso + "</strong>");
            out.print("</div>");
        }


    }

    private void addErrorSummary(JspWriter out, List<ValidationMessage> errors) throws IOException {
        out.print("<div class=\"alert alert-danger alert-dismissable\">");
        out.print("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">×</button>");
        out.print(format("<strong>%s</strong><br/>", this.text));

        for (final ValidationMessage message : errors) {
            out.print(format("<p>%s</p>", message.getMessage()));
        }
        out.print("</div>");
    }

    public void setText(final String text) {
        this.text = text;
    }
}

