package org.springfx.stage

import javafx.stage.Stage
import org.springfx.scene.Projection

/**
 * A projector.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface Projector {

    Stage getStage()

    void show(Projection projection)
}