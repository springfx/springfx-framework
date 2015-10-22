package org.springfx.fxml

import javafx.fxml.FXMLLoader
import javafx.util.Callback
import org.springframework.beans.factory.FactoryBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springfx.context.ApplicationContextUtils

/**
 * A factory bean for {@link FXMLLoader}s.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class FXMLLoaderFactory implements FactoryBean<FXMLLoader>, ApplicationContextAware {

    static final BEAN_NAME = 'fxmlLoader'

    ApplicationContext applicationContext

    @Override
    FXMLLoader getObject() throws Exception {
        def loader = new FXMLLoader()
        loader.setClassLoader(applicationContext.classLoader)
        loader.controllerFactory = new Callback<Class<?>, Object>() {
            @Override
            Object call(Class<?> controllerType) {
                def controller = ApplicationContextUtils.getBeanOrInstance(
                        applicationContext, controllerType)
                controller
            }
        }
        loader
    }

    @Override
    Class<?> getObjectType() {
        FXMLLoader
    }

    @Override
    final boolean isSingleton() {
        false
    }
}
