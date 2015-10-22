package org.springfx.validation

import javafx.beans.value.ObservableValue
import org.springframework.validation.Errors
import org.springframework.validation.Validator

/**
 *
 * @author Stephan Grundner
 */
abstract class ObservableValueValidator<T extends ObservableValue> implements Validator {

    @Override
    boolean supports(Class<?> clazz) {
        ObservableValue.isAssignableFrom(clazz)
    }

    @Override
    void validate(Object target, Errors errors) {
        validate((T) target, errors)
    }

    abstract void validate(T target, Errors errors)
}
