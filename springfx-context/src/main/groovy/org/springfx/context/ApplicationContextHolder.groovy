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
        // TODO Provide other strategies
        strategy = new DefaultApplicationContextHolderStrategy()
    }

    /**
     * Bind the specified {@link org.springframework.context.ApplicationContext} instance to the current JavaFX thread.
     *
     * @see {org.springfx.context.ApplicationContextBinder#bindContext}
     * @param applicationContext An inactive! application context
     * @param application The application to get bound
     * @param primaryStage The primary stage of the application
     */
    static void bindContext(ConfigurableApplicationContext applicationContext, Application application, Stage primaryStage) {
        strategy.bindContext(applicationContext, application, primaryStage)
    }

    static ApplicationContext getContext() {
        strategy.getApplicationContext()
    }
}
