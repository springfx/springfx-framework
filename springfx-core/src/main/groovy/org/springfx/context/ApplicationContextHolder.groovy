package org.springfx.context

import javafx.application.Application
import javafx.stage.Stage
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 * Associates a given {@link ApplicationContext} with the current JavaFX thread.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class ApplicationContextHolder {

    private static ApplicationContextHolderStrategy strategy

    static {
        strategy = new ThreadLocalApplicationContextHolderStrategy()
    }

    static void bindContext(ConfigurableApplicationContext applicationContext, Application application, Stage primaryStage) {
        strategy.bindContext(applicationContext, application, primaryStage)
    }

    static ApplicationContext getApplicationContext() {
        strategy.getApplicationContext()
    }
}
