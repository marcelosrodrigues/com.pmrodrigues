package com.pmodrigues.pageobjects.annotations;

import java.lang.annotation.*;

/**
 * Created by Marceloo on 02/10/2015.
 */
@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface URLs {
    URL[] urls();

}
