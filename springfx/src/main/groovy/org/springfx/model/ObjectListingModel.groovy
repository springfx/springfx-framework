package org.springfx.model

import javafx.beans.property.ObjectProperty
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList

/**
 *
 * @author Stephan Grundner
 */
class ObjectListingModel implements ListingModel {

    private final ObjectProperty<ObservableList<Model>> itemsProperty = new SimpleObjectProperty<>(this, 'items')

    @Override
    ReadOnlyProperty<ObservableList<Model>> itemsProperty() {
        itemsProperty
    }

    @Override
    ObservableList<Model> getItems() {
        itemsProperty().getValue()
    }
}
