package org.springfx.crud.scene

import org.springfx.control.FormPane
import org.springfx.scene.Projection

/**
 *
 * @author Stephan Grundner
 */
interface EditorProjection extends Projection {

    FormPane getForm()
}