package org.springfx.crud.scene

import javafx.scene.control.Label
import javafx.scene.control.TextField
import org.springfx.control.FormPane
import org.springfx.scene.AnnotationConfigProjectionAdapter
import org.springfx.scene.Projection
import org.springfx.scene.ProjectionSource

/**
 *
 * @author Stephan Grundner
 */
class SimpleEditorProjection<T> implements EditorProjection {

    final Projection adapter

    FormPane form

    SimpleEditorProjection() {
        adapter = new AnnotationConfigProjectionAdapter(this)
        form = new FormPane()
        form.add(new Label("ID"), new TextField(), 0, 0)
        form.add(new Label("First Name"), new TextField(), 0, 1)
        form.add(new Label("Address"), new TextField(), 0, 2)
    }

    @ProjectionSource(Projection.PRIMARY_SOURCE)
    FormPane getForm() {
        form
    }

    @Override
    Object getProjectionSource(String key) {
        adapter.getProjectionSource(key)
    }
}
