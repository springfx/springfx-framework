package org.springfx.theater

import javafx.application.Platform
import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext
import org.springfx.boot.Application
import org.springfx.theater.config.TheaterConfiguration
import org.springfx.theater.scene.Scenario
import org.springfx.theater.stage.Arrangement

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
abstract class Theater extends Application {

    private ApplicationContext applicationContext

    protected ApplicationContext getApplicationContext() {
        applicationContext
    }

    @Override
    protected SpringApplicationBuilder createBuilder() {
        super.createBuilder().sources(TheaterConfiguration)
    }

    protected Arrangement getPrimaryArrangement() {
        applicationContext.getBean(Arrangement)
    }

    protected Scenario getPrimaryScenario() {
        applicationContext.getBean(Scenario)
    }

    protected void start(Arrangement arrangement) {
        arrangement.perform(primaryScenario)
    }

    @Override
    final void start(Stage primaryStage, ApplicationContext applicationContext) {
        this.applicationContext = applicationContext
        Platform.implicitExit = true
        start(primaryArrangement)
    }
}
