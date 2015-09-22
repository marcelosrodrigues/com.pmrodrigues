package com.pmrodrigues.taglib;

import static java.lang.String.format;

/**
 * Created by Marceloo on 13/01/2015.
 */
public class InputPasswordTag extends InputTextTag {

    protected String getTag() {
        return format("<input type=\"password\" value=\"%s\" id=\"%s\" name=\"%s\" class=\"form-control\" placeholder=\"%s\" %s/>",
                this.getValue() == null ? "" : this.getValue(), this.getId(), this.getId(), getLabel(), super.isDisable() ? "disabled=\"disabled\"" : "");
    }
}
