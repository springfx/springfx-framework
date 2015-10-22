package org.springfx.context

import javafx.application.Application
import javafx.stage.Stage
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext

/**
 * Implementation for {@link ApplicationContextHolderStrategy}
 * using a {@link InheritableThreadLocal} variable to store the application context.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class DefaultApplicationContextHolderStrategy implements ApplicationContextHolderStrategy {

    private static final ThreadLocal<ApplicationContext> contextHolder =
            new InheritableThreadLocal<ApplicationContext>()

    @Override
    ApplicationContext getApplicationContext() {
        contextHolder.get()
    }

    @Override
    void bindContext(ConfigurableApplicationContext applicationContext, Application application, Stage primaryStage) {
        def binder = ApplicationContextUtils.getBeanOrInstance(applicationContext,
                ApplicationContextBinder, DefaultApplicationContextBinder)

        binder.bindContext(applicationContext, application, primaryStage)
        contextHolder.set(applicationContext)
    }
}
