package org.springfx.model

import javafx.beans.property.ReadOnlyProperty
import javafx.collections.ObservableList

/**
 *
 * @author Stephan Grundner
 */
interface ListingModel {

    ReadOnlyProperty<ObservableList<Model>> itemsProperty()

    ObservableList<Model> getItems()
}