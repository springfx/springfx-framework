package org.springfx.validation

import javafx.beans.property.ObjectProperty
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.beans.property.SimpleObjectProperty

/**
 *
 * @author Stephan Grundner
 */
trait DataBindingSupport {

    protected final ObjectProperty<ControlDataBinder> dataBinder =
            new SimpleObjectProperty<>(this, 'dataBinder', new ControlDataBinder(this))

    ReadOnlyObjectProperty<ControlDataBinder> dataBinderProperty() {
        dataBinder
    }

    ControlDataBinder getDataBinder() {
        dataBinderProperty().get()
    }
}
