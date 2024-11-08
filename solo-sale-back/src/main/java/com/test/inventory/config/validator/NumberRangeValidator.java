package com.test.inventory.config.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumberRangeValidator implements ConstraintValidator<NumberRange, Number> {

    private double minValue;
    private double maxValue;

    @Override
    public void initialize(NumberRange constraintAnnotation) {
        this.minValue = constraintAnnotation.min();
        this.maxValue = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        // If the value is null, it's considered valid
        if (value == null) {
            return false;
        }

        // Convert the number to a double for comparison
        double doubleValue = value.doubleValue();

        // Check if the value is within the specified range
        return doubleValue >= minValue && doubleValue <= maxValue;
    }
}