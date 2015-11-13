package org.springfx

import org.springfx.scene.Projection

/**
 * A controller.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface Controller<P extends Projection> {

    P getProjection()
}