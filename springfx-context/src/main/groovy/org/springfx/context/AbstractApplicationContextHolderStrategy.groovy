package org.springfx.context

import javafx.application.Application
import javafx.application.Platform
import javafx.stage.Stage
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.config.SingletonBeanRegistry
import org.springframework.beans.factory.support.AbstractBeanDefinition
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext
import org.springfx.fxml.FXMLLoaderFactory

/**
 * Initializes a given {@link ApplicationContext} instance.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
abstract class AbstractApplicationContextHolderStrategy implements ApplicationContextHolderStrategy {

    /**
     * Register the {@link Application} singleton bean.
     *
     * @param beanRegistry The bean registry
     * @param application The application instance to get registered
     */
    protected void registerApplicationSingleton(SingletonBeanRegistry beanRegistry, Application application) {
        beanRegistry.registerSingleton(ApplicationContextHolder.APPLICATION_BEAN_NAME, application)
    }

    /**
     * Register the primary {@link Stage} singleton bean.
     *
     * @param beanRegistry The bean registry
     * @param primaryStage The primary stage to get registered
     */
    protected void registerPrimaryStageSingleton(SingletonBeanRegistry beanRegistry, Stage primaryStage) {
        beanRegistry.registerSingleton(ApplicationContextHolder.PRIMARY_STAGE_BEAN_NAME, primaryStage)
    }

    /**
     * Register a bean definition for {@link javafx.fxml.FXMLLoader} instances.
     *
     * @param beanDefinitionRegistry The bean definition registry
     */
    protected void registerFXMLLoader(BeanDefinitionRegistry beanDefinitionRegistry) {
        def builder = BeanDefinitionBuilder
                .genericBeanDefinition(FXMLLoaderFactory)
                .setScope(BeanDefinition.SCOPE_PROTOTYPE)
                .setLazyInit(true)
                .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
        def beanDefinition = builder.beanDefinition
        beanDefinition.primary = true
        def name = BeanDefinitionReaderUtils.generateBeanName(beanDefinition, beanDefinitionRegistry)
        beanDefinitionRegistry.registerBeanDefinition(name, builder.beanDefinition)
    }

    @Override
    void bindContext(ConfigurableApplicationContext applicationContext, Application application, Stage primaryStage) {
        assert Platform.isFxApplicationThread(), "Unexpected thread [${Thread.currentThread().name}]"
        assert !applicationContext.active
        assert applicationContext.parent == null

        Thread.currentThread().contextClassLoader = applicationContext.classLoader

        applicationContext.addBeanFactoryPostProcessor(new BeanFactoryPostProcessor() {
            @Override
            void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                registerApplicationSingleton(beanFactory, application)
                registerPrimaryStageSingleton(beanFactory, primaryStage)
                registerFXMLLoader((BeanDefinitionRegistry) beanFactory)
            }
        })

        this.applicationContext = applicationContext
    }

    abstract void setApplicationContext(ApplicationContext applicationContext)
}
