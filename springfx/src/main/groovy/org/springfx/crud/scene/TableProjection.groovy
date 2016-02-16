package org.springfx.crud.scene

import javafx.scene.control.TableView

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface TableProjection<T> extends CrudProjection {

    TableView<T> getTable()
}