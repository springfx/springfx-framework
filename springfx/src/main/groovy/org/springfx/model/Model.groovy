package org.springfx.model

import javafx.beans.value.ObservableValue
import javafx.collections.ObservableMap

/**
 *
 * @author Stephan Grundner
 */
interface Model {

    ObservableMap<String, ObservableValue<?>> getProperties()
}