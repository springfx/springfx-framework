package org.springfx.validation

import javafx.scene.control.Control
import org.springframework.validation.MapBindingResult
import org.springframework.validation.Validator
import org.springfx.beans.property.ReadOnlyPropertyAccessor
import org.springfx.beans.property.TextInputControlReadOnlyPropertyAccessor

/**
 *
 * @author Stephan Grundner
 */
class ControlValidator<C extends Control> {

    private final C control
    private final Validator validator

    private ReadOnlyPropertyAccessor propertyAccessor

    ControlValidator(C control, Validator validator) {
        assert control != null
        assert validator != null
        this.control = control
        this.validator = validator
        propertyAccessor = propertyAccessorFor(control)
    }

    protected ReadOnlyPropertyAccessor propertyAccessorFor(C control) {
        new TextInputControlReadOnlyPropertyAccessor()
    }

    void validate() {
        def property = propertyAccessor.property(control)
        def errors = new MapBindingResult([:], property.name)
        validator.validate(property, errors)

        if (errors.hasErrors()) {
            ValidationUtils.applyErrorStyleClass(control)
            println errors.toString()
        } else {
            ValidationUtils.clearErrorStyleClass(control)
        }
    }
}
