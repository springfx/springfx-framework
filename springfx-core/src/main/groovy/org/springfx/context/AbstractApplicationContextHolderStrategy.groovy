package org.springfx.context

import javafx.application.Application
import javafx.stage.Stage
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.beans.factory.support.AbstractBeanDefinition
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext
import org.springfx.fxml.FXMLLoaderFactory

/**
 * Base class for storing application context information against an JavaFX thread.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
abstract class AbstractApplicationContextHolderStrategy implements ApplicationContextHolderStrategy {

    abstract void setApplicationContext(ApplicationContext applicationContext)

    @Override
    void bindContext(ConfigurableApplicationContext applicationContext, Application application, Stage primaryStage) {
        def beanFactory = applicationContext.getBeanFactory()
        beanFactory.registerSingleton(ApplicationContextUtils.APPLICATION_BEAN_NAME, application)
        beanFactory.registerSingleton(ApplicationContextUtils.PRIMARY_STAGE_BEAN_NAME, primaryStage)

        if (beanFactory instanceof BeanDefinitionRegistry) {
            def fxmlLoaderBuilder = BeanDefinitionBuilder
                    .genericBeanDefinition(FXMLLoaderFactory)
                    .setScope('prototype')
                    .setLazyInit(false)
                    .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME)
            beanFactory.registerBeanDefinition('fxmlLoader', fxmlLoaderBuilder.beanDefinition)
        }

        beanFactory = applicationContext.getAutowireCapableBeanFactory()
        beanFactory.autowireBeanProperties(application, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false)

        this.applicationContext = applicationContext
    }
}
