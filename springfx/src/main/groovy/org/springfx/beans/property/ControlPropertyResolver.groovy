package org.springfx.beans.property

import javafx.beans.property.Property
import javafx.scene.control.Control

/**
 * Ease access to properties of {@link Control}s.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface ControlPropertyResolver {

    Property<?> getProperty(Control control, String name)
    Property<?> getPrimaryProperty(Control control)
}