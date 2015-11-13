package org.springfx.context

import javafx.application.Application
import javafx.stage.Stage
import org.springframework.context.ConfigurableApplicationContext

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface ApplicationContextBinder {

    /**
     * Bind the specified {@link org.springframework.context.ApplicationContext} instance to the current JavaFX thread.
     *
     * @param applicationContext The application context to get bound
     * @param application The application instance
     * @param primaryStage The primary stage
     */
    void bindContext(ConfigurableApplicationContext applicationContext, Application application, Stage primaryStage)
}
