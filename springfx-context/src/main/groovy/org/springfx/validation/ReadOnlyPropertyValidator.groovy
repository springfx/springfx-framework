package org.springfx.validation

import javafx.beans.property.ReadOnlyProperty

/**
 *
 * @author Stephan Grundner
 */
abstract class ReadOnlyPropertyValidator<T extends ReadOnlyProperty> extends ObservableValueValidator<T> {

    @Override
    boolean supports(Class<?> clazz) {
        ReadOnlyProperty.isAssignableFrom(clazz)
    }
}
