package com.pmodrigues.pageobjects;

import com.pmodrigues.pageobjects.annotations.*;
import com.pmodrigues.pageobjects.tags.Tag;
import com.pmodrigues.pageobjects.tags.TagFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.lang.reflect.Field;
import java.util.Collection;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;
import static org.reflections.ReflectionUtils.getAllFields;
import static org.reflections.ReflectionUtils.withAnnotation;

public abstract class AbstractPageObject {

    private WebDriver driver;

    public AbstractPageObject() {
        URL url = this.getClass().getAnnotation(URL.class);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        driver = new ChromeDriver(options);
        driver.get(url.url());
    }

    public AbstractPageObject(final WebDriver driver) {
        this.driver = driver;
    }

    public void close() {
        this.driver.close();
        this.driver.quit();
    }

    public AbstractPageObject submit() throws Exception {
        Action action = this.getClass().getAnnotation(Action.class);
        AbstractPageObject page = this.update();

        if (!isBlankOrNull(action.byClass())) {
            page = page.clickByClass(action.byClass());
        }

        if (!isBlankOrNull(action.byXpath())) {
            page = page.clickByXpath(action.byXpath());
        }

        if (!isBlankOrNull(action.byId())) {
            page = page.clickById(action.byId());
        }

        if (!isBlankOrNull(action.byName())) {
            page = page.clickByName(action.byName());
        }
        page.populate();
        return page;
    }

    private AbstractPageObject clickByXpath(String xpath) throws Exception {
        driver.findElement(By.xpath(xpath)).click();
        return PageObjectFactory.create(driver);
    }

    private AbstractPageObject clickByClass(final String aClass) throws Exception {
        driver.findElement(By.className(aClass)).click();
        return PageObjectFactory.create(driver);
    }


    public AbstractPageObject clickById(final String id) throws Exception {
        driver.findElement(By.id(id)).click();
        return PageObjectFactory.create(driver);
    }

    public AbstractPageObject clickByName(final String name) throws Exception {
        driver.findElement(By.name(name)).click();
        return PageObjectFactory.create(driver);
    }

    public AbstractPageObject goTo(final String url) throws Exception {
        this.driver.navigate().to(url);
        AbstractPageObject next = PageObjectFactory.create(driver);
        next.populate();
        return next;
    }

    public AbstractPageObject goTo(final AbstractPageObject to) throws IllegalAccessException {
        URL url = to.getClass().getAnnotation(URL.class);
        this.driver.navigate().to(url.url());
        to.populate();
        return to;
    }

    public AbstractPageObject goTo(Class<? extends AbstractPageObject> toPage) throws Exception {
        final AbstractPageObject page = toPage.getConstructor(WebDriver.class)
                .newInstance(this.driver);
        page.url(toPage.getAnnotation(URL.class).url());

        populate(page);

        return page;
    }

    protected AbstractPageObject populate() throws IllegalAccessException {
        return populate(this);
    }

    protected AbstractPageObject populate(final AbstractPageObject page) throws IllegalAccessException {

        Collection<Field> atributos = getAllFields(page.getClass(), withAnnotation(ById.class));

        for (final Field atributo : atributos) {
            final ById byid = atributo.getAnnotation(ById.class);
            final Tag tag = TagFactory.getInstance(driver).createById(byid.value());
            carregaValoresDoCampo(page, atributo, tag);
        }

        atributos = getAllFields(page.getClass(), withAnnotation(ByClass.class));
        for (final Field atributo : atributos) {
            final ByClass byid = atributo.getAnnotation(ByClass.class);
            final Tag tag = TagFactory.getInstance(driver).createByClass(byid.value());
            carregaValoresDoCampo(page, atributo, tag);
        }

        atributos = getAllFields(page.getClass(), withAnnotation(ByXPath.class));
        for (final Field atributo : atributos) {
            final ByXPath byid = atributo.getAnnotation(ByXPath.class);
            final Tag tag = TagFactory.getInstance(driver).createByXPath(byid.value());
            carregaValoresDoCampo(page, atributo, tag);
        }

        atributos = getAllFields(page.getClass(), withAnnotation(ByName.class));
        for (final Field atributo : atributos) {
            final ByName byid = atributo.getAnnotation(ByName.class);
            final Tag tag = TagFactory.getInstance(driver).createByName(byid.value());
            carregaValoresDoCampo(page, atributo, tag);
        }

        return page;
    }

    private void carregaValoresDoCampo(final AbstractPageObject page, final Field atributo, final Tag tag) throws IllegalAccessException {
        atributo.setAccessible(true);
        if (atributo.getType().equals(String.class)) {
            atributo.set(page, tag.getValue());
        } else if (atributo.getType().equals(Boolean.class)) {
            atributo.set(page, Boolean.valueOf(tag.getValue().toString()));
        }
    }

    private AbstractPageObject url(final String url) {
        driver.navigate().to(url);
        return this;
    }

    public AbstractPageObject update() throws IllegalAccessException {
        return update(this);
    }

    public AbstractPageObject update(final AbstractPageObject page) throws IllegalAccessException {

        Collection<Field> atributos = getAllFields(page.getClass(), withAnnotation(ById.class));

        for (final Field atributo : atributos) {
            atributo.setAccessible(true);
            final ById byid = atributo.getAnnotation(ById.class);
            final Object value = atributo.get(page);
            Tag tag = TagFactory.getInstance(driver).createById(byid.value());
            tag.setValue(value);
        }

        atributos = getAllFields(page.getClass(), withAnnotation(ByXPath.class));

        for (final Field atributo : atributos) {
            atributo.setAccessible(true);
            final ByXPath byid = atributo.getAnnotation(ByXPath.class);
            final Object value = atributo.get(page);
            Tag tag = TagFactory.getInstance(driver).createByXPath(byid.value());
            tag.setValue(value);
        }

        atributos = getAllFields(page.getClass(), withAnnotation(ByClass.class));

        for (final Field atributo : atributos) {
            atributo.setAccessible(true);
            final ByClass byid = atributo.getAnnotation(ByClass.class);
            final Object value = atributo.get(page);
            Tag tag = TagFactory.getInstance(driver).createByClass(byid.value());
            tag.setValue(value);
        }

        atributos = getAllFields(page.getClass(), withAnnotation(ByName.class));

        for (final Field atributo : atributos) {
            atributo.setAccessible(true);
            final ByName byid = atributo.getAnnotation(ByName.class);
            final Object value = atributo.get(page);
            Tag tag = TagFactory.getInstance(driver).createByName(byid.value());
            tag.setValue(value);
        }

        return page;

    }

    public String getPageSource() {
        return this.driver.getPageSource();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
