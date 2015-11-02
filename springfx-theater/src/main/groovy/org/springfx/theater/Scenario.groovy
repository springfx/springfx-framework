package org.springfx.theater

import javafx.beans.property.ReadOnlyProperty
import javafx.scene.Scene
import javafx.stage.Stage

/**
 *
 * @author Stephan Grundner
 */
interface Scenario {

    Stage getStage()

    Scene getScene()
    ReadOnlyProperty<Scene> sceneProperty()

    void perform()
    boolean isPerforming()
}