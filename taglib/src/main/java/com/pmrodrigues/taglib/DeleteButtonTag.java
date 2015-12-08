package com.pmrodrigues.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static java.lang.String.format;

/**
 * Created by Marceloo on 23/11/2015.
 */
public class DeleteButtonTag extends SimpleTagSupport {

    private static final String BUTTON_TEMPLATE = "<button type=\"submit\" class=\"btn btn-danger btn-circle\"><i class=\"fa fa-trash-o\"></i></button>";

    private String action;

    private Object value;

    private String fieldName;

    private String message;

    @Override
    public void doTag() throws JspException, IOException {
        final JspWriter writer = this.getJspContext().getOut();

        writer.println(format("<form action=\"%s\" method=\"post\" onsubmit=\"return doDelete('%s');\">", this.action, this.message));
        writer.println(format("<input type=\"hidden\" name=\"%s\" value=\"%s\" />", this.fieldName, this.value));
        writer.println(BUTTON_TEMPLATE);
        writer.println("</form>");
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
