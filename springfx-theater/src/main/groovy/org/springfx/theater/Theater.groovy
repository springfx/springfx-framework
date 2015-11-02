package org.springfx.theater

import javafx.scene.control.MenuBar
import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext
import org.springfx.boot.Application

/**
 *
 * @author Stephan Grundner
 */
abstract class Theater extends Application {

    private MenuBar menuBar

    @Override
    protected SpringApplicationBuilder createBuilder() {
        super.createBuilder().showBanner(false).sources(TheaterConfiguration)
    }

    protected MenuBar buildMenuBar() {
        menuBar = new MenuBar()
        menuBar.useSystemMenuBar = true
        menuBar
    }

    MenuBar getMenuBar() {
        menuBar
    }

    abstract void start(Scenario primaryScenario)

    private void initDataSource() {

    }

    @Override
    final void start(Stage primaryStage, ApplicationContext applicationContext) {
        menuBar = buildMenuBar()
        def primaryScenario = applicationContext.getBean(Scenario)
        assert primaryScenario != null, "primaryScenario must not be null"
        start(primaryScenario)
        if (!primaryStage.showing) {
            primaryStage.show()
        }
    }
}
