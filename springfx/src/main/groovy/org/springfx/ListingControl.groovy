package org.springfx

import javafx.collections.ObservableList
import javafx.scene.control.ListView
import javafx.scene.control.TableView

/**
 *
 * @author Stephan Grundner
 */
interface ListingControl<T> {

    ListView<T> getList()
    TableView<T> getTable()
    ObservableList<T> getItems()
}