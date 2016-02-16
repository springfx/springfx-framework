package org.springfx

import javafx.stage.Stage
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springfx.service.DefaultProjectionService
import org.springfx.service.ProjectionService
import org.springfx.stage.DefaultProjector
import org.springfx.stage.Projector

/**
 *
 * @author Stephan Grundner
 */
@Configuration
class DefaultConfiguration {

    @Bean
    Projector defaultProjector(Stage stage) {
        new DefaultProjector(stage)
    }

    @Bean
    ProjectionService projectionService(ListableBeanFactory beanFactory, Projector projector) {
        new DefaultProjectionService(beanFactory, projector)
    }
}

