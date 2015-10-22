package org.springfx.validation

import javafx.beans.property.ReadOnlyStringProperty
import org.springframework.validation.Errors

import java.util.regex.Pattern

/**
 *
 * @author Stephan Grundner
 */
class RegexPropertyValidator extends ReadOnlyPropertyValidator<ReadOnlyStringProperty> {

    private final Pattern pattern

    RegexPropertyValidator(Pattern pattern) {
        this.pattern = pattern
    }

    RegexPropertyValidator(String regex) {
        this(Pattern.compile(regex))
    }

    @Override
    boolean supports(Class<?> clazz) {
        ReadOnlyStringProperty.isAssignableFrom(clazz)
    }

    @Override
    void validate(ReadOnlyStringProperty target, Errors errors) {
        def m = pattern.matcher(target.value)
        if (!m.matches()) {
            errors.rejectValue(target.name, 'dont.match.regex')
        }
    }
}
