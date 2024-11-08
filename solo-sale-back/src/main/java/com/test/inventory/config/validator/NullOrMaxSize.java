package com.test.inventory.config.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NullOrMaxSizeValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrMaxSize {

    String message() default "Field must be either null or have a maximum length of 100 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int max() default 100; // Default max length is set to 100
}