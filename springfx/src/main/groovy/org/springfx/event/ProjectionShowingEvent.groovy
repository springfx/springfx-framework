package org.springfx.event

import org.springframework.context.ApplicationEvent
import org.springfx.scene.Projection

/**
 *
 * @author Stephan Grundner
 */
class ProjectionShowingEvent extends ApplicationEvent {

    final Projection projection

    ProjectionShowingEvent(Object source, Projection projection) {
        super(source)
        this.projection = projection
    }
}
