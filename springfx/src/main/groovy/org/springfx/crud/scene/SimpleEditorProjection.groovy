package org.springfx.crud.scene

import javafx.scene.Node
import org.springfx.control.FormPane

/**
 *
 * @author Stephan Grundner
 */
class SimpleEditorProjection implements EditorProjection {

    FormPane editor

    SimpleEditorProjection() {
        editor = new FormPane()
    }

    @Override
    Object getProjectionSource(String areaName) {
        editor
    }
}
