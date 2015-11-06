package org.springfx.theater.stage

import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import org.springfx.theater.scene.Scenario

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class SimpleBorderArrangement implements BorderArrangement {

    final Stage stage
    BorderPane borderPane

    SimpleBorderArrangement(Stage stage) {
        this.stage = stage
    }

    @Override
    void perform(Scenario scenario) {
        if (borderPane == null) {
            borderPane = new BorderPane()
        }
        scenario.perform(this)

        def scene = stage.scene
        if (scene == null) {
            scene = new Scene(borderPane)
            stage.scene = scene
        } else {
            scene.root = borderPane
        }

        if (!stage.showing) {
            stage.show()
        }
    }

    @Override
    Node getCenter() {
        borderPane.center
    }

    @Override
    void setCenter(Node center) {
        borderPane.center = center
    }
}
