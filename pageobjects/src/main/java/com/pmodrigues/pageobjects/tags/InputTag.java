package com.pmodrigues.pageobjects.tags;

import org.openqa.selenium.WebElement;

/**
 * Created by Marceloo on 06/01/2015.
 */
public class InputTag implements Tag {

    private final WebElement element;

    public InputTag(final WebElement element) {
        this.element = element;
    }

    @Override
    public Object getValue() {
        return element.getAttribute("value");
    }

    @Override
    public void setValue(final Object value) {

        for (int i = 0; i < value.toString().length(); i++) {
            element.sendKeys("\u0008");
        }

        element.sendKeys(value.toString());
    }
}
