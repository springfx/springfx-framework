package org.springfx.beans.property

import javafx.beans.property.ReadOnlyProperty
import javafx.scene.control.Control

/**
 *
 * @author Stephan Grundner
 */
interface ReadOnlyPropertyAccessor<P extends ReadOnlyProperty> {

    boolean supports(Class<?> clazz)

    P property(Control control)
}