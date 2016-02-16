package org.springfx.service

import org.springfx.scene.Projection
import org.springfx.stage.Projector

/**
 *
 * @author Stephan Grundner
 */
interface ProjectionService {

    Projector getProjector()

    void show(Class<? extends Projection> projectionClass, Object key)
    void show(Class<? extends Projection> projectionClass)
}
