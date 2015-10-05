package com.pmodrigues.pageobjects;

import com.pmodrigues.pageobjects.annotations.URL;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPageObject {

    private WebDriver driver;

    public AbstractPageObject(final WebDriver driver) {
        this.driver = driver;
        URL url = this.getClass().getAnnotation(URL.class);
        driver.get(url.value());
    }

    public void close() {
        this.driver.close();
    }

    public abstract AbstractPageObject submit();

    public AbstractPageObject navigateTo(Class<? extends AbstractPageObject> toPage) throws Exception {
        final AbstractPageObject page = toPage.getConstructor(WebDriver.class)
                .newInstance(this.driver);
        page.url(toPage.getAnnotation(URL.class).value());

        return page;
    }

    private AbstractPageObject url(final String url) {
        driver.get(url);
        return this;
    }



    public String getPageSource() {
        return this.driver.getPageSource();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
