package org.springfx.boot

import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springfx.context.ApplicationContextHolder
import org.springfx.context.ApplicationContextUtils

/**
 * Base class from which SpringFX applications extend.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
abstract class Application extends javafx.application.Application implements ApplicationContextInitializer {

    private boolean initialized
    private boolean running

    private Stage primaryStage

    @Override
    final void init() throws Exception {
        assert !initialized
        initialized = true
    }

    void initialize(ConfigurableApplicationContext applicationContext) {
        ApplicationContextHolder.bindContext(applicationContext, this, primaryStage)
    }

    protected SpringApplicationBuilder createBuilder() {
        new SpringApplicationBuilder()
                .initializers(this)
                .web(false)
    }

    abstract void start(Stage primaryStage, ApplicationContext applicationContext)

    @Override
    final void start(Stage primaryStage) throws Exception {
        assert initialized && !running
        this.primaryStage = primaryStage

        def applicationContext = createBuilder().run(parameters.raw as String[])
        ApplicationContextUtils.autowireBeanPropertiesByType(applicationContext, this)
        start(primaryStage, applicationContext)

        running = true
    }

    @Override
    final void stop() throws Exception {
        assert running
        def applicationContext = ApplicationContextHolder.applicationContext
        ApplicationContextUtils.close(applicationContext)
        running = false
    }
}
