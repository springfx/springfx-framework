package org.springfx.beans.property

import javafx.beans.property.ReadOnlyStringProperty
import javafx.scene.control.Control
import javafx.scene.control.TextInputControl

/**
 *
 * @author Stephan Grundner
 */
class TextInputControlReadOnlyPropertyAccessor implements ReadOnlyPropertyAccessor<ReadOnlyStringProperty> {

    @Override
    boolean supports(Class<?> clazz) {
        TextInputControl.isAssignableFrom(clazz)
    }

    @Override
    ReadOnlyStringProperty property(Control control) {
        ((TextInputControl) control).textProperty()
    }
}
