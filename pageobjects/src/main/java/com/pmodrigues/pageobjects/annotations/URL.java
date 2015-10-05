package com.pmodrigues.pageobjects.annotations;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface URL {
    String value() default "http://localhost:8080";
}
