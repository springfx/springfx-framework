package org.springfx.context

import javafx.application.Application
import javafx.stage.Stage
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext

/**
 * Associates a given {@link ApplicationContext} with the current JavaFX thread.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class ApplicationContextHolder {

    static final APPLICATION_BEAN_NAME = 'application'
    static final PRIMARY_STAGE_BEAN_NAME = 'primaryStage'

    private static ApplicationContextHolderStrategy strategy

    static {
        strategy = new DefaultApplicationContextHolderStrategy()
    }

    static void bindContext(ConfigurableApplicationContext applicationContext, Application application, Stage primaryStage) {
        strategy.bindContext(applicationContext, application, primaryStage)
    }

    static ApplicationContext getContext() {
        strategy.getApplicationContext()
    }
}
