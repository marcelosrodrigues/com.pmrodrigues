package com.pmrodrigues.taglib;

/**
 * Created by Marceloo on 14/04/2015.
 */
public class RadioListTag extends CheckboxListTag {

    @Override
    protected String getType() {
        return "radio";
    }
}
