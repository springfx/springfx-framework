package org.springfx

import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ConfigurableApplicationContext
import org.springfx.context.ApplicationContextUtils
import org.springfx.scene.Projection
import org.springfx.service.ProjectionService

/**
 * Spring FX application class.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class Application extends javafx.application.Application {

    private ConfigurableApplicationContext applicationContext
    private Launcher launcher

    protected Application(Class<?>... sources) {
        launcher = new Launcher().sources(sources)
    }

    public Application() {

    }

    ApplicationInitializer createInitializer(Stage primaryStage) {
        new ApplicationInitializer(this, primaryStage)
    }

    @Override
    void init() throws Exception {
        if (launcher == null) {
            launcher = Launcher.get()
        }
    }

    @Override
    void start(Stage primaryStage) throws Exception {
        def builder = new SpringApplicationBuilder()
        def initializer = createInitializer(primaryStage)
        applicationContext =  builder.initializers(initializer)
                .sources(ApplicationConfiguration)
                .sources(launcher.sources as Object[])
                .bannerMode(launcher.bannerMode)
                .headless(false)
                .web(false)
                .run(parameters.raw as String[])

        ApplicationContextUtils.autowireBeanPropertiesByType(applicationContext, this)

        if (launcher.usingProjections) {
            startUsingProjections()
        } else {
            def scene = applicationContext.getBean(Scene)
            primaryStage.scene = scene
            if (!primaryStage.showing) {
                primaryStage.show()
            }
        }
    }

    protected void startUsingProjections() {
        def projectionService = applicationContext.getBean(ProjectionService)
        projectionService.show(Projection)
    }

    @Override
    void stop() throws Exception {
        applicationContext.stop()
    }
}
