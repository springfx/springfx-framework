package org.springfx.theater

import javafx.beans.property.Property
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.scene.Scene
import javafx.stage.Stage

/**
 *
 * @author Stephan Grundner
 */
abstract class AbstractScenario implements Scenario {

    final Stage stage

    private final Property<Scene> sceneProperty = new SimpleObjectProperty<>(this, 'scene')

    AbstractScenario(Stage stage) {
        this.stage = stage
    }

    @Override
    Scene getScene() {
        sceneProperty.getValue()
    }

    protected void setScene(Scene scene) {
        sceneProperty.setValue(scene)
    }

    @Override
    ReadOnlyProperty<Scene> sceneProperty() {
        sceneProperty
    }

    @Override
    void perform() {
        stage.scene = scene
    }

    @Override
    boolean isPerforming() {
        stage.scene == scene
    }
}
