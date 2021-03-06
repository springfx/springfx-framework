package org.springfx.context

import org.springframework.beans.BeanInstantiationException
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanFactoryUtils
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext
import org.springfx.context.i18n.LocaleChangedEvent

/**
 * Utilities for simplify usage of {@link ApplicationContext}.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
final class ApplicationContextUtils {

    static void notifyLocaleChanged(ApplicationContext applicationContext) {
        applicationContext.publishEvent(new LocaleChangedEvent(applicationContext))
    }

    /**
     * Get the unique bean name for the specified type.
     *
     * @param applicationContext
     * @param type
     * @return The unique bean name for the specified type or null
     * @throws NoUniqueBeanDefinitionException
     */
    static String getUniqueBeanNameForType(ListableBeanFactory beanFactory, Class<?> type) throws NoUniqueBeanDefinitionException {
//        def beanNames = context.getBeanNamesForType(type)
        def beanNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, type)
        if (beanNames.size() > 1) {
            throw new NoUniqueBeanDefinitionException(type, beanNames)
        }
        beanNames.length == 1 ? beanNames.first() : null
    }

    /**
     * Get the bean for the specified name, or a new instance of the specified fallback type if no bean is available.
     *
     * @param applicationContext
     * @param name
     * @param fallbackType
     * @param args
     * @return The bean for the specified type, or a new instance of the specified fallback type if no bean is available
     * @throws BeanInstantiationException
     * @throws NoUniqueBeanDefinitionException
     */
    static def <T> T getBeanOrInstance(BeanFactory context, String name, Class<? extends T> fallbackType, Object... args) throws BeanInstantiationException, NoUniqueBeanDefinitionException {
        T instance
        if (name?.size() > 0 && context.containsBean(name)) {
            if (args?.length > 0) {
                instance = (T) context.getBean(name, args)
            } else {
                instance = (T) context.getBean(name)
            }
        } else {
            instance = fallbackType.newInstance(args as Object[])
        }
        instance
    }

    /**
     * Get the bean for the specified type, or a new instance if no bean is available.
     *
     * @param type
     * @param args
     * @return The bean for the specified type, or a new instance if no bean is available
     * @throws BeanInstantiationException
     * @throws NoUniqueBeanDefinitionException
     */
    static def <T> T getBeanOrInstance(ListableBeanFactory context, Class<T> beanType, Object... args) throws NoSuchBeanDefinitionException {
        def beanNames = context.getBeanNamesForType(beanType)
        if (beanNames.length > 0) {
            if (beanNames.length == 1) {
                return (T) context.getBean(beanNames[0], args)
            }
            throw new NoUniqueBeanDefinitionException(beanType, beanNames)
        }
        args?.length > 0 ? beanType.newInstance(args) : beanType.newInstance()
    }

    /**
     * Get the bean for the specified type, or a new instance of the specified fallback type if no bean is available.
     *
     * @param type
     * @param fallbackType
     * @param args
     * @return The bean for the specified type, or a new instance of the specified fallback type if no bean is available
     * @throws BeanInstantiationException
     * @throws NoUniqueBeanDefinitionException
     */
    static def <T> T getBeanOrInstance(ListableBeanFactory context, Class<T> type, Class<? extends T> fallbackType, Object... args) throws BeanInstantiationException, NoUniqueBeanDefinitionException {
        def beanName = getUniqueBeanNameForType(context, type)
        getBeanOrInstance(context, (String) beanName, fallbackType, args)
    }

    static def Object getSingletonBean(ApplicationContext context, String name) throws BeanInstantiationException {
        if (!context.isSingleton(name)) {
            def beanClass = context.getType(name)
            throw new BeanInstantiationException(beanClass, "Singleton bean required")
        }
        context.getBean(name)
    }

    static def <T> T getSingletonBean(ApplicationContext context, Class<T> type) throws BeanInstantiationException, NoUniqueBeanDefinitionException {
        def beanName = getUniqueBeanNameForType(context, type)
        (T) getSingletonBean(context, beanName)
    }

    static def Object getPrototypeBean(ApplicationContext context, String name, Object... args) throws BeanInstantiationException {
        if (!context.isPrototype(name)) {
            def beanClass = context.getType(name)
            throw new BeanInstantiationException(beanClass, "Prototype bean required")
        }
        context.getBean(name, args)
    }

    static def <T> T getPrototypeBean(ApplicationContext context, Class<T> type, Object... args) throws BeanInstantiationException, NoUniqueBeanDefinitionException {
        def beanName = getUniqueBeanNameForType(context, type)
        (T) getPrototypeBean(context, beanName, args)
    }

    static def <T> T getPrototypeBean(ApplicationContext context, Class<T> type) throws BeanInstantiationException, NoUniqueBeanDefinitionException {
        (T) getPrototypeBean(context, type, null)
    }

    static void autowireBeanProperties(ConfigurableApplicationContext context, Object existingBean, int autowireMode = AutowireCapableBeanFactory.AUTOWIRE_NO,  boolean dependencyCheck = false) {
        def beanFactory = context.autowireCapableBeanFactory
        beanFactory.autowireBeanProperties(existingBean, autowireMode, false)
    }

    static void autowireBeanPropertiesByName(ConfigurableApplicationContext context, Object existingBean, boolean dependencyCheck = false) {
        autowireBeanProperties(context, existingBean, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, dependencyCheck)
    }

    static void autowireBeanPropertiesByType(ConfigurableApplicationContext context, Object existingBean, boolean dependencyCheck = false) {
        autowireBeanProperties(context, existingBean, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, dependencyCheck)
    }

    static boolean containsBeanDefinition(ListableBeanFactory beanFactory, Class<?> type) {
        def beanName = getUniqueBeanNameForType(beanFactory, type)
        if (beanName) {
            beanFactory.containsBeanDefinition(beanName)
        }
        false
    }

    static void close(ApplicationContext context) {
        if (context instanceof ConfigurableApplicationContext) {
            context?.close()
        }
    }

    private ApplicationContextUtils() {}
}
