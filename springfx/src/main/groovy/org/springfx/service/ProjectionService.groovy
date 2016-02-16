package org.springfx.service

import org.springfx.scene.Projection
import org.springfx.stage.Projector

/**
 *
 * @author Stephan Grundner
 */
interface ProjectionService {

    Projector getProjector()

    void show(String beanName, Object id)
    void show(String beanName)

    void show(Class<? extends Projection> projectionClass, Object id)
    void show(Class<? extends Projection> projectionClass)
}
