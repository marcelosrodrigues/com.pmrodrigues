package com.pmrodrigues.persistence.validation.constraintvalidations;

import com.pmrodrigues.persistence.validation.annotations.NotTransient;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.persistence.Id;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Marceloo on 29/01/2016.
 */
public class NotTransientValidation implements ConstraintValidator<NotTransient, Object> {
    private NotTransient notTransient;

    @Override
    public void initialize(final NotTransient notTransient) {

        this.notTransient = notTransient;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        try {

            List<Field> fields = FieldUtils.getAllFieldsList(value.getClass());

            for (Field field : fields) {

                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    return (field.get(value) != null && (Long) field.get(value) > 0L);
                }

            }

            return true;
        } catch (IllegalAccessException e) {
            return false;
        }


    }
}
