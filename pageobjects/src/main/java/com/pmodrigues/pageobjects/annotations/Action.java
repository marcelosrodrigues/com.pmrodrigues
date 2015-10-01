package com.pmodrigues.pageobjects.annotations;

import java.lang.annotation.*;

/**
 * Created by Marceloo on 30/09/2015.
 */
@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String byClass() default "";

    String byId() default "";

    String byName() default "";

    String byXpath() default "";
}
