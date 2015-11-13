package org.springfx

import javafx.stage.Stage
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

/**
 *
 * @author Stephan Grundner
 */
class ApplicationInitializer implements ApplicationContextInitializer {

    final javafx.application.Application application
    final Stage primaryStage

    ApplicationInitializer(javafx.application.Application application, Stage primaryStage) {
        this.application = application
        this.primaryStage = primaryStage
    }

    void initialize(ConfigurableApplicationContext applicationContext) {
        applicationContext.beanFactory.registerSingleton('application', application)
        applicationContext.beanFactory.registerSingleton('primaryStage', primaryStage)
    }
}
