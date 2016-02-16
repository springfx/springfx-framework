package org.springfx.crud.scene

import javafx.scene.control.Button
import javafx.scene.control.TableView
import org.springfx.scene.AnnotationConfigProjectionAdapter
import org.springfx.scene.Projection
import org.springfx.scene.ProjectionSource

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class SimpleTableProjection<T> implements TableProjection<T> {

    TableView<T> table
    Button blaaa

    final Projection adapter

    SimpleTableProjection() {
        adapter = new AnnotationConfigProjectionAdapter(this)
        table = new TableView<>()
        blaaa = new Button("Blaaaa")
    }

    @Override
    Object getProjectionSource(String key) {
        adapter.getProjectionSource(key)
    }

    @ProjectionSource(Projection.PRIMARY_SOURCE)
    TableView<T> getTable() {
        return table
    }

    @ProjectionSource('create-button')
    Button getBlaaa() {
        return blaaa
    }
}
