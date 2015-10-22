package org.springfx.context

import javafx.application.Application
import javafx.application.Platform
import javafx.stage.Stage
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.config.SingletonBeanRegistry
import org.springframework.beans.factory.support.AbstractBeanDefinition
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.support.GenericApplicationContext
import org.springfx.fxml.FXMLLoaderFactory

/**
 *
 * @author Stephan Grundner
 */
abstract class AbstractApplicationContextHolderStrategy implements ApplicationContextHolderStrategy {

    protected void registerApplicationSingleton(SingletonBeanRegistry beanRegistry, Application application) {
        beanRegistry.registerSingleton(ApplicationContextHolder.APPLICATION_BEAN_NAME, application)
    }

    protected void registerPrimaryStageSingleton(SingletonBeanRegistry beanRegistry, Stage primaryStage) {
        beanRegistry.registerSingleton(ApplicationContextHolder.PRIMARY_STAGE_BEAN_NAME, primaryStage)
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
        assert Platform.isFxApplicationThread(), "Unexpected thread [${Thread.currentThread().name}]"
        assert !applicationContext.active
        assert applicationContext.parent == null

        def root = new GenericApplicationContext()
        root.with {
            registerApplicationSingleton(beanFactory, application)
            registerPrimaryStageSingleton(beanFactory, primaryStage)
            registerFXMLLoaderPrototype((BeanDefinitionRegistry) beanFactory)
            refresh()
        }

        applicationContext.parent = root
        applicationContext.refresh()
        ApplicationContextUtils.autowireBeanPropertiesByType(applicationContext, application)

        this.applicationContext = applicationContext
    }

    abstract void setApplicationContext(ApplicationContext applicationContext)
}
