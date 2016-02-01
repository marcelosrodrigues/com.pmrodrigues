package com.pmrodrigues.persistence.validation.annotations;

import com.pmrodrigues.persistence.validation.constraintvalidations.NotTransientValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by Marceloo on 29/01/2016.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})
@Constraint(validatedBy = NotTransientValidation.class)
public @interface NotTransient {

    String message() default "{com.pmrodrigues.persistence.NotTransient.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
