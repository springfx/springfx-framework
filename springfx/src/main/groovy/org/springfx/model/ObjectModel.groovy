package org.springfx.model

import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.collections.ObservableMap

/**
 *
 * @author Stephan Grundner
 */
class ObjectModel implements Model {

    final ObservableMap<String, ObservableValue<?>> properties = FXCollections.observableArrayList()
}
