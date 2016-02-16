package org.springfx.service

import org.springframework.beans.factory.BeanFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springfx.event.ProjectionShowingEvent
import org.springfx.scene.Projection
import org.springfx.stage.Projector

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class DefaultProjectionService implements ProjectionService, ApplicationEventPublisherAware {

    ApplicationEventPublisher applicationEventPublisher

    BeanFactory beanFactory
    Projector projector

    final Map<String, Projection> projections = [:]

    DefaultProjectionService(BeanFactory beanFactory, Projector projector) {
        this.beanFactory = beanFactory
        this.projector = projector
    }

    void show(Projection projection) {
        projector.show(projection)

        applicationEventPublisher.publishEvent(new ProjectionShowingEvent(
                applicationEventPublisher, projection))
    }

    final String buildUniqueKey(Class<? extends Projection> projectionClass, Object key) {
        "${projectionClass.name}#$key"
    }

    @Override
    void show(Class<? extends Projection> projectionClass, Object key) {
        def uniqueKey = buildUniqueKey(projectionClass, key)
        def projection = projections.get(uniqueKey)
        if (projection) {
            show(projection)
        } else {
            projection = beanFactory.getBean(projectionClass)
            projections.put(uniqueKey, projection)
            show(projection)
        }
    }

    @Override
    void show(Class<? extends Projection> projectionClass) {
        show(projectionClass, 0)
    }

}
