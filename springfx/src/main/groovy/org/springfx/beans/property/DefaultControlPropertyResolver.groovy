package org.springfx.beans.property

import javafx.beans.property.Property
import javafx.scene.control.Control
import javafx.scene.control.TextInputControl

/**
 * Default implementation for {@link ControlPropertyResolver}.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class DefaultControlPropertyResolver implements ControlPropertyResolver {

    @Override
    Property<?> getProperty(Control control, String name) {
        PropertyUtils.getProperty(control, name)
    }

    @Override
    Property<?> getPrimaryProperty(Control control) {
        if (control instanceof TextInputControl) {
            return control.textProperty()
        }
        throw new RuntimeException("Unsupported control type [$control]")
    }
}
