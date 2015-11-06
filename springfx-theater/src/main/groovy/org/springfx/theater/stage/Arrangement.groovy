package org.springfx.theater.stage

import javafx.stage.Stage
import org.springfx.theater.scene.Scenario

/**
 *
 * @author Stephan Grundner
 */
interface Arrangement {

    Stage getStage()

    void perform(Scenario scenario)
}