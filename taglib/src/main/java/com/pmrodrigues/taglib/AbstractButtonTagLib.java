package com.pmrodrigues.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static java.lang.String.format;

/**
 * Created by Marceloo on 25/05/2015.
 */
public abstract class AbstractButtonTagLib extends SimpleTagSupport {

    private String label;

    private String action;

    private static final String BUTTON_TEMPLATE = "<button name=\"acao\" value=\"%s\" class=\"btn btn-info\"><i class=\"fa %s\"></i> %s</button>";

    @Override
    public void doTag() throws JspException, IOException {

        final JspWriter out = this.getJspContext().getOut();

        out.print(format(this.getTemplate(), this.action, this.getIcone(), this.getLabel()));

    }

    protected String getTemplate() {
        return BUTTON_TEMPLATE;
    }

    public abstract String getIcone();

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public void setAction(final String action) {
        this.action = action;
    }
}
