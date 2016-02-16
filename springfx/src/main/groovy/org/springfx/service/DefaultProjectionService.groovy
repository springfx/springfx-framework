package org.springfx.service

import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.ListableBeanFactory
import org.springfx.context.ApplicationContextUtils
import org.springfx.scene.Projection
import org.springfx.stage.Projector

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class DefaultProjectionService implements ProjectionService {

    ListableBeanFactory beanFactory
    Projector projector

    final Map<String, Projection> projections = [:]

    DefaultProjectionService(ListableBeanFactory beanFactory, Projector projector) {
        this.beanFactory = beanFactory
        this.projector = projector
    }

    DefaultProjectionService(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory
        projector = ApplicationContextUtils.getBeanOrInstance(beanFactory, )
    }

    void show(Projection projection) {
        projector.show(projection)
    }

    @Override
    void show(String beanName, Object id) {
        def key = id ? "$beanName#$id" : beanName
        def projection = projections.get(key)
        if (projection == null) {
            projection = beanFactory.getBean(beanName) as Projection
            projections.put(key, projection)
        }

        show(projection)
    }

    @Override
    void show(String beanName) {
        show(beanName, null)
    }

    @Override
    void show(Class<? extends Projection> projectionClass, Object id) {
        def beanName = ApplicationContextUtils.getUniqueBeanNameForType(
                beanFactory, projectionClass)
        show(beanName, id)
    }

    @Override
    void show(Class<? extends Projection> projectionClass) {
        show(projectionClass, null)
    }
}
