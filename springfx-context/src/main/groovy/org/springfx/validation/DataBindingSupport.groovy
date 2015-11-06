package org.springfx.validation

import javafx.beans.property.ObjectProperty
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.beans.property.SimpleObjectProperty
import org.springframework.context.ApplicationContext

/**
 *
 * @author Stephan Grundner
 */
trait DataBindingSupport {

    protected ObjectProperty<ControlDataBinder> dataBinder


    abstract private ApplicationContext getApplicationContext()

    ReadOnlyObjectProperty<ControlDataBinder> dataBinderProperty() {
        if (dataBinder == null) {
            dataBinder = new SimpleObjectProperty<>(this, 'dataBinder',
                    new ControlDataBinder(applicationContext, this))
        }
        dataBinder
    }

    ControlDataBinder getDataBinder() {
        dataBinderProperty().get()
    }
}
