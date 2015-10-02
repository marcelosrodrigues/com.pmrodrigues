package com.pmodrigues.pageobjects;

import com.pmodrigues.pageobjects.annotations.URL;
import com.pmodrigues.pageobjects.annotations.URLs;
import org.apache.commons.collections.Predicate;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;

import java.util.Set;

import static java.lang.String.format;
import static org.apache.commons.collections.CollectionUtils.find;

/**
 * Created by Marceloo on 01/10/2015.
 */
public final class PageObjectFactory {

    private static final Reflections reflections = new Reflections();
    private static final Set<Class<? extends AbstractPageObject>> pages =
            (Set<Class<? extends AbstractPageObject>>) reflections.getSubTypesOf(AbstractPageObject.class);

    public static AbstractPageObject create(final WebDriver driver) throws Exception {
        final Class<? extends AbstractPageObject> next = (Class<? extends AbstractPageObject>) find(pages, new Predicate() {
            @Override
            public boolean evaluate(final Object object) {
                Class<AbstractPageObject> clazz = (Class<AbstractPageObject>) object;
                if (clazz.isAnnotationPresent(URL.class)) {
                    final URL url = clazz.getAnnotation(URL.class);
                    return driver.getCurrentUrl().equals(url.url());
                } else if (clazz.isAnnotationPresent(URLs.class)) {
                    for (URL url : clazz.getAnnotation(URLs.class).urls()) {
                        if (!driver.getCurrentUrl().equals(url.url())) {
                            continue;
                        } else {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            }
        });

        if (next == null) {
            throw new Exception(format("Nenhum formulário encontrado para o endereço %s", driver.getCurrentUrl()));
        }

        return next.getConstructor(WebDriver.class)
                .newInstance(driver);
    }
}
