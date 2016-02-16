package org.springfx

import javafx.application.Platform
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContextInitializer
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

    /**
     * Application initializer.
     *
     * Registers the application and the primary stage on startup.
     */
    protected class Initializer implements ApplicationContextInitializer {

        final Stage primaryStage

        Initializer(Stage primaryStage) {
            this.primaryStage = primaryStage
        }

        void initialize(ConfigurableApplicationContext applicationContext) {
            def beanFactory = applicationContext.beanFactory
            beanFactory.registerSingleton('application', Application.this)
            beanFactory.registerSingleton('primaryStage', primaryStage)
        }
    }

    private ConfigurableApplicationContext applicationContext
    private Launcher launcher

    protected Application(Class<?>... sources) {
        launcher = new Launcher().sources(sources)
    }

    public Application() {

    }

    @Override
    void init() throws Exception {
        if (launcher == null) {
            launcher = Launcher.get()
        }
    }

    protected Initializer createInitializer(Stage primaryStage) {
        new Initializer(primaryStage)
    }

    @Override
    void start(Stage primaryStage) throws Exception {
        Platform.implicitExit = launcher.implicitExit
        def builder = new SpringApplicationBuilder()
        applicationContext =  builder.sources(launcher.sources as Object[])
                .initializers(createInitializer(primaryStage))
                .bannerMode(launcher.bannerMode)
                .headless(false)
                .web(false)
                .run(parameters.raw as String[])

        ApplicationContextUtils.autowireBeanPropertiesByType(applicationContext, this)

        if (launcher.projections) {
            def projectionService = applicationContext
                    .getBean(ProjectionService)
            projectionService.show(Projection)
        } else {
            def scene = applicationContext.getBean(Scene)
            primaryStage.scene = scene
            if (!primaryStage.showing) {
                primaryStage.show()
            }
        }
    }

    @Override
    void stop() throws Exception {
        applicationContext.stop()
    }
}
