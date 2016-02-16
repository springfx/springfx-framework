package org.springfx.stage

import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.springfx.scene.Projection

/**
 *
 * @author Stephan Grundner
 */
class DefaultProjector implements Projector {

    final Stage stage

    Pane pane

    DefaultProjector(Stage stage) {
        this.stage = stage
    }

    void show(Projection projection) {
        if (pane == null) {
            pane = new VBox()
        }

        def source = (Node) projection.getProjectionSource(
                Projection.PRIMARY_SOURCE)

        pane.children.setAll(source)

        def scene = stage.scene
        if (scene == null) {
            scene = new Scene(pane)
        } else {
            scene.root = pane
        }
        stage.scene = scene

        if (!stage.showing) {
            stage.show()
        }
    }
}
