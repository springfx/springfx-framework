package org.springfx.stage

import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ToolBar
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import org.springfx.crud.scene.ListingProjection
import org.springfx.scene.Projection

/**
 *
 * @author Stephan Grundner
 */
class DefaultProjector implements Projector {

    final Stage stage

    ToolBar toolBar
    BorderPane borderPane

    DefaultProjector(Stage stage) {
        this.stage = stage
    }

    void show(Projection projection) {
        if (borderPane == null) {
            borderPane = new BorderPane()
            toolBar = new ToolBar()
            borderPane.top = toolBar
        }

        def primarySource = projection.getProjectionSource(Projection.PRIMARY_SOURCE)
        if (primarySource instanceof Node) {
            borderPane.center = (Node) primarySource
        }

        def createButton = projection.getProjectionSource(ListingProjection.CREATE_BUTTON_SOURCE)
        if (createButton instanceof Button) {
            toolBar.items.add(0, createButton)
        }

        def scene = stage.scene
        if (scene == null) {
            scene = new Scene(borderPane)
        } else {
            scene.root = borderPane
        }
        stage.scene = scene

        if (!stage.showing) {
            stage.show()
        }
    }
}
