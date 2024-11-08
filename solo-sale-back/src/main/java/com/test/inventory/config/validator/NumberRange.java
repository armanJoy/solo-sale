package com.test.inventory.config.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NumberRangeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberRange {

    String message() default "Field value must be within the specified range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    double min() default 0; // Default minimum value

    double max() default Integer.MAX_VALUE; // Default maximum value
}