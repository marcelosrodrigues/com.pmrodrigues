package com.pmrodrigues.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static java.lang.String.format;

/**
 * Created by Marceloo on 23/11/2015.
 */
public class NovoButtonTagLib extends SimpleTagSupport {

    private static final String BUTTON_TEMPLATE = "<a href=\"%s\" class=\"btn btn-success\"><i class=\"fa fa-plus\"></i> %s</a>";

    private String url;

    private String label;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println(format(BUTTON_TEMPLATE, getURL(), this.label));

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getURL() {
        final PageContext pageContext = (PageContext) this.getJspContext();
        final String context = ((HttpServletRequest) pageContext.getRequest()).getContextPath();

        if ("/".equalsIgnoreCase(context)) {
            return format("%s", this.url);
        } else {
            return format("%s%s", context, this.url);
        }

    }

    public void setLabel(String label) {
        this.label = label;
    }
}
