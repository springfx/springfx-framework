package org.springfx.context

import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.config.SingletonBeanRegistry
import org.springframework.beans.factory.support.AbstractBeanDefinition
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.context.ConfigurableApplicationContext
import org.springfx.fxml.FXMLLoaderFactory

/**
 *
 * @author Stephan Grundner
 */
class DefaultApplicationContextBinder implements ApplicationContextBinder {

    protected void registerApplicationSingleton(SingletonBeanRegistry beanRegistry, Application application) {
        beanRegistry.registerSingleton(ApplicationContextHolder.APPLICATION_BEAN_NAME, application)
    }

    protected void registerPrimaryStageSingleton(SingletonBeanRegistry beanRegistry, Stage primaryStage) {
        beanRegistry.registerSingleton(ApplicationContextHolder.PRIMARY_STAGE_BEAN_NAME, primaryStage)
        primaryStage.sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
                newValue.stylesheets.add('spring.css')
            }
        })
    }

    protected void registerFXMLLoaderPrototype(BeanDefinitionRegistry beanDefinitionRegistry) {
        def builder = BeanDefinitionBuilder
                .genericBeanDefinition(FXMLLoaderFactory)
                .setScope(BeanDefinition.SCOPE_PROTOTYPE)
                .setLazyInit(true)
                .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME)
        beanDefinitionRegistry.registerBeanDefinition(
                FXMLLoaderFactory.BEAN_NAME, builder.beanDefinition)
    }

    @Override
    void bindContext(ConfigurableApplicationContext applicationContext, Application application, Stage primaryStage) {
        def beanFactory = applicationContext.getBeanFactory()
        registerApplicationSingleton(beanFactory, application)
        registerPrimaryStageSingleton(beanFactory, primaryStage)
        registerFXMLLoaderPrototype((BeanDefinitionRegistry) beanFactory)

        ApplicationContextUtils.autowireBeanPropertiesByType(applicationContext, application)
    }
}
