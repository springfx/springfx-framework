package org.springfx.context

import org.springframework.context.ApplicationContext

/**
 * Implementation for {@link ApplicationContextHolderStrategy}
 * using a {@link InheritableThreadLocal} variable to store the application context.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class DefaultApplicationContextHolderStrategy extends AbstractApplicationContextHolderStrategy {

    private static final ThreadLocal<ApplicationContext> contextHolder =
            new InheritableThreadLocal<ApplicationContext>()

    @Override
    ApplicationContext getApplicationContext() {
        contextHolder.get()
    }

    @Override
    void setApplicationContext(ApplicationContext applicationContext) {
        contextHolder.set(applicationContext)
    }
}
