package com.test.inventory.config.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrMaxSizeValidator implements ConstraintValidator<NullOrMaxSize, String> {

    private int maxLength;

    @Override
    public void initialize(NullOrMaxSize constraintAnnotation) {
        this.maxLength = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // If the string is not null, check if it meets the max length requirement
        return value == null || value.length() <= maxLength;
    }
}