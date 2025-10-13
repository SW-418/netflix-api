package samwells.io.netflix_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {
    private final Set<String> allowedValues = new HashSet<>();
    private boolean required;
    
    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        allowedValues.addAll(
        Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(String::valueOf)
                .toList()
        );
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Skip validation if not required and value is empty
        if (!required && (value == null || value.isBlank())) return true;
        if (allowedValues.contains(value)) return true;

        // Otherwise provide meaningful error message
        context.disableDefaultConstraintViolation();
        String errorMessage = String.format("Invalid value: %s - Must be one of: %s", value, allowedValues);
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();

        return false;
    }
}
