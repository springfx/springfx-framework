package org.springfx.validation

import javafx.beans.property.ReadOnlyStringProperty
import org.springframework.validation.Errors

/**
 *
 * @author Stephan Grundner
 */
class StringPropertyValidator extends ReadOnlyPropertyValidator<ReadOnlyStringProperty> {

    @Override
    void validate(ReadOnlyStringProperty target, Errors errors) {
        if (target.value == "") {
            errors.rejectValue(target.name, 'not.null')
        }
    }
}
