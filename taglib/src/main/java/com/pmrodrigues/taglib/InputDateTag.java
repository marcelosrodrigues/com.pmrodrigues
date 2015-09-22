package com.pmrodrigues.taglib;

import org.apache.commons.validator.GenericValidator;

import static java.lang.String.format;

/**
 * Created by Marceloo on 14/04/2015.
 */
public class InputDateTag extends InputTextTag {

    private String pattern;

    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    protected String getType() {
        return "date";
    }

    @Override
    protected String getTag() {
        return format("<input type=\"%s\" value=\"%s\" id=\"%s\" name=\"%s\" class=\"form-control\" placeholder=\"%s\" %s/>",
                getType(),
                GenericValidator.isBlankOrNull(super.getValue()) ? "" : super.getValue(), super.getId(),
                super.getId(),
                super.getLabel(),
                GenericValidator.isBlankOrNull(pattern) ? "" : "pattern=\"" + pattern + "\"");
    }
}
