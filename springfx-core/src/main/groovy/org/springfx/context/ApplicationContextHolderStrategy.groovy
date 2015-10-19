package org.springfx.context

import javafx.application.Application
import javafx.stage.Stage
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext

/**
 * A strategy for storing application context information against an JavaFX thread.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface ApplicationContextHolderStrategy {

    /**
     * Bind the specified {@link ApplicationContext} instance to the current JavaFX thread.
     *
     * @param applicationContext The application context to get bound
     * @param application The application instance
     * @param primaryStage The primary stage
     */
    void bindContext(ConfigurableApplicationContext applicationContext, Application application, Stage primaryStage)

    /**
     * Get the {@link ApplicationContext} instance bound to the current JavaFX thread.
     *
     * @return The {@link ApplicationContext} instance bound to the current JavaFX thread
     */
    ApplicationContext getApplicationContext()
}