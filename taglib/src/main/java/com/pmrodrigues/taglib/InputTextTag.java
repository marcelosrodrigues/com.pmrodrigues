package com.pmrodrigues.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

import static java.lang.String.format;

/**
 * Created by Marceloo on 13/01/2015.
 */
public class InputTextTag extends AbstractTagLib {

    private String label;
    private String value;
    private String id;
    private boolean show = true;
    private boolean disabled = false;

    public void setId(final String id) {
        this.id = id;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public void setShowLabel(final boolean show) {
        this.show = show;
    }

    public void setDisabled(final boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public void doTag() throws JspException, IOException {
        final JspWriter writer = getJspContext().getOut();

        final String errorCss = this.getError();

        writer.print(format("<div class=\"form-group %s\">", errorCss));
        if (show) {
            writer.print(format("<label for=\"%s\" class=\"control-label\">%s</label>", this.id, label));
        }
        writer.print(getTag());
        writer.print("</div>");

        writer.flush();
    }

    protected String getTag() {
        return format("<input type=\"text\" value=\"%s\" id=\"%s\" name=\"%s\" class=\"form-control\" placeholder=\"%s\" %s/>",
                this.value == null ? "" : this.value, this.id, this.id, label, disabled ? "disabled=\"disabled\"" : "");
    }

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public boolean isDisable() {
        return disabled;
    }

    public void setDisable(final Boolean disabled) {
        this.disabled = disabled;
    }
}
