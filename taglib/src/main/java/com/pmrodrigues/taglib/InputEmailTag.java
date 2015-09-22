package com.pmrodrigues.taglib;

/**
 * Created by Marceloo on 14/04/2015.
 */
public class InputEmailTag extends InputDateTag {

    @Override
    protected String getType() {
        return "email";
    }
}
