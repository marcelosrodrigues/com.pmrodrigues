package com.pmodrigues.pageobjects;

import com.pmodrigues.pageobjects.annotations.URL;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPageObject {

    private WebDriver driver;

    public AbstractPageObject(final WebDriver driver) {
        this(driver, false);
    }

    public AbstractPageObject(final WebDriver driver, final boolean navigateTo) {
        this.driver = driver;
        if (!navigateTo) {
            URL url = this.getClass().getAnnotation(URL.class);
            driver.get(url.value());
        }
    }

    public void close() {
        this.driver.close();
    }

    public abstract AbstractPageObject submit();

    public abstract AbstractPageObject abrir(final String value) throws Exception;

    public <E extends AbstractPageObject> E navigateTo(Class<? extends AbstractPageObject> toPage) throws Exception {
        final AbstractPageObject page = toPage.getConstructor(WebDriver.class)
                .newInstance(this.driver);
        page.url(toPage.getAnnotation(URL.class).value());

        return (E) page;
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
