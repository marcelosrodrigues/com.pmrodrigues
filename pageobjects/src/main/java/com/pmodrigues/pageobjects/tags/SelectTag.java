package com.pmodrigues.pageobjects.tags;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Marceloo on 06/01/2015.
 */
public class SelectTag implements Tag {
    private final WebElement element;

    public SelectTag(final WebElement element) {
        this.element = element;
    }

    @Override
    public Object getValue() {
        final Select select = new Select(element);
        return select.getFirstSelectedOption().getAttribute("value");
    }

    @Override
    public void setValue(final Object value) {
        final Select select = new Select(element);
        select.selectByValue(String.valueOf(value));
    }
}
