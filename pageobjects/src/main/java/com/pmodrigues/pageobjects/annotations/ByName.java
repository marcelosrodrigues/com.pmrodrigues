package com.pmodrigues.pageobjects.annotations;

import java.lang.annotation.*;

/**
 * Created by Marceloo on 30/09/2015.
 */
@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ByName {
    String value();
}
