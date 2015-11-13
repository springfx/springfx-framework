package org.springfx.crud.scene

import javafx.scene.control.TableView
import org.springfx.scene.Projection
import org.springfx.scene.ProjectionSource

/**
 *
 * @author Stephan Grundner
 */
class ListProjection<T> {

    @ProjectionSource(Projection.PRIMARY_SOURCE)
    TableView<T> table

    ListProjection(TableView<T> table) {
        this.table = table
    }

    ListProjection() {
        table = new TableView<>()
    }
}
