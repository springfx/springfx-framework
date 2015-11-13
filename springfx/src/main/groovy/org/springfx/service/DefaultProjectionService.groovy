package org.springfx.service

import org.springframework.beans.factory.BeanFactory
import org.springfx.scene.Projection
import org.springfx.stage.Projector

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class DefaultProjectionService implements ProjectionService {

    BeanFactory beanFactory
    Projector projector

    DefaultProjectionService(BeanFactory beanFactory, Projector projector) {
        this.beanFactory = beanFactory
        this.projector = projector
    }

    void show(Projection projection) {
        projector.show(projection)
    }

    @Override
    void show(Class<? extends Projection> projectionClass) {
        def projection = beanFactory.getBean(projectionClass)
        show(projection)
    }
}
