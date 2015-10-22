package org.springfx.context

import org.springframework.context.ApplicationContext

/**
 * A strategy for storing application context information against an JavaFX thread.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface ApplicationContextHolderStrategy extends ApplicationContextBinder {

    /**
     * Get the {@link ApplicationContext} instance bound to the current JavaFX thread.
     *
     * @return The {@link ApplicationContext} instance bound to the current JavaFX thread
     */
    ApplicationContext getApplicationContext()
}