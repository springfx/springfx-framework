package org.springfx.stage

import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.control.ButtonBase
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.control.ToolBar
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import org.springfx.beans.property.PropertyUtils
import org.springfx.scene.Projection

/**
 *
 * @author Stephan Grundner
 */
class DefaultProjector implements Projector {

    final Stage stage

    ToolBar toolBar
    BorderPane borderPane
    TabPane tabPane

    DefaultProjector(Stage stage) {
        this.stage = stage
    }

    void show(Projection projection) {
        if (borderPane == null) {
            borderPane = new BorderPane()
            tabPane = new TabPane()
            def selectionModel = tabPane.selectionModel
            PropertyUtils.onChange(selectionModel.selectedItemProperty(), this.&tabSelected)
            borderPane.center = tabPane
            toolBar = new ToolBar()
            borderPane.top = toolBar
        }

        def tabs = tabPane.tabs
        def tab = tabs.find { it.content == projection }
        if (tab == null) {
            tab = new Tab(projection.toString())
            tab.closable = false
            tab.properties.put(Projection.name, projection)
            tabs.add(tab)
        } else {
            tabPane.selectionModel.select(tab)
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

    void tabSelected(Tab tab) {
        toolBar.items.clear()

        def projection = tab.properties.get(Projection.name) as Projection
        def primarySource = projection.getProjectionSource(Projection.PRIMARY_SOURCE)
        if (primarySource instanceof Node) {
            tab.content = (Node) primarySource
        }

        def createButton = projection.getProjectionSource('create-button')
        if (createButton instanceof ButtonBase) {
            toolBar.items.add(createButton)
        }
    }
}
