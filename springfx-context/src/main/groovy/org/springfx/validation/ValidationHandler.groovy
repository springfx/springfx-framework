package org.springfx.validation

import javafx.beans.property.Property
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError

/**
 *
 * @author Stephan Grundner
 */
@FunctionalInterface
interface ValidationHandler {

    void onGlobalError(ObjectError objectError)
    void onFieldError(FieldError fieldError, Property<?> controlValueProperty)
    void onFieldSuccess(Property<?> controlValueProperty)
}