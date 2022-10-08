package com.tonyhc.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingPasswordValidator implements ConstraintValidator<MatchingPassword, Object> {
    private String password;
    private String confirmPassword;
    private String message;

    @Override
    public void initialize(MatchingPassword constraintAnnotation) {
        password = constraintAnnotation.first();
        confirmPassword = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;

        try {
            final Object firstObj = new BeanWrapperImpl(value).getPropertyValue(password);
            final Object secondObj = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);

            valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {
            // we can ignore
        }

        // If Validation fails, build a violation report
        if (!valid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(password)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
