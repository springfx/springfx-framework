package org.springfx.crud.scene

import javafx.scene.Node
import org.springfx.scene.Projection

/**
 *
 * @author Stephan Grundner
 */
interface EditorProjection extends Projection {

    Node getEditor()
}