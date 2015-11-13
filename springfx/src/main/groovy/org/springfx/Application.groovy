package org.springfx

import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ConfigurableApplicationContext
import org.springfx.context.ApplicationContextUtils
import org.springfx.scene.Projection
import org.springfx.service.ProjectionService

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class Application extends javafx.application.Application {

    private ConfigurableApplicationContext applicationContext
    private Launcher launcher

    ApplicationInitializer createInitializer(Stage primaryStage) {
        new ApplicationInitializer(this, primaryStage)
    }

    @Autowired
    ProjectionService viewService

    @Override
    void init() throws Exception {
        launcher = Launcher.get()
    }

    @Override
    void start(Stage primaryStage) throws Exception {
        def builder = new SpringApplicationBuilder()
        def initializer = createInitializer(primaryStage)
        applicationContext =  builder.initializers(initializer)
                .sources(ApplicationConfiguration)
                .sources(launcher.sources as Object[])
                .run(parameters.raw as String[])

        ApplicationContextUtils.autowireBeanPropertiesByType(applicationContext, this)

        viewService.show(Projection)
    }

    @Override
    void stop() throws Exception {
        applicationContext.stop()
    }
}
