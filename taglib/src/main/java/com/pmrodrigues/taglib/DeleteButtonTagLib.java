package com.pmrodrigues.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static java.lang.String.format;

/**
 * Created by Marceloo on 31/05/2015.
 */
public class DeleteButtonTagLib extends SimpleTagSupport {

    private boolean inlist = false;

    private String action;

    private String label = "";

    private String title = "";

    private String message = "";

    public static final String BUTTON_TEMPLATE = "<button onclick=\"javascript:doDelete(this);\" action=\"%s\" type=\"button\" class=\"btn btn-danger %s\" title=\"%s\" message=\"%s\">" +
            "<i class=\"fa %s\"> %s</i>" +
            "</button>";

    @Override
    public void doTag() throws JspException, IOException {
        final JspWriter out = getJspContext().getOut();

        out.print(format(BUTTON_TEMPLATE,
                action,
                inlist ? "btn-circle" : "",
                title,
                message,
                "fa-times",
                this.label));
    }

    public void setIsInList(final Boolean inlist) {
        this.inlist = inlist;
    }

    public void setAction(final String url) {
        this.action = url;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
