package org.springfx.context

import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.ApplicationContext

/**
 * Utilities for simplify usage of {@link ApplicationContext}.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
final class ApplicationContextUtils {

    static final APPLICATION_BEAN_NAME = 'application'
    static final PRIMARY_STAGE_BEAN_NAME = 'primaryStage'

    static def <T> T getBeanOrInstance(ApplicationContext applicationContext, Class<T> beanType, Object... args) throws NoSuchBeanDefinitionException {
        def beanNames = applicationContext.getBeanNamesForType(beanType)
        if (beanNames.length > 0) {
            if (beanNames.length == 1) {
                return applicationContext.getBean(beanNames[0], args)
            }
            throw new NoUniqueBeanDefinitionException(beanType, beanNames)
        }
        beanType.newInstance(args)
    }

    private ApplicationContextUtils() {}
}
