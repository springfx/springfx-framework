package org.springfx

import javafx.stage.Stage
import org.springframework.beans.factory.BeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springfx.command.CommandFactory
import org.springfx.command.DefaultCommandFactory
import org.springfx.service.DefaultProjectionService
import org.springfx.service.ProjectionService
import org.springfx.stage.DefaultProjector
import org.springfx.stage.Projector

/**
 *
 * @author Stephan Grundner
 */
@Configuration
class ApplicationConfiguration {

    @Bean
    CommandFactory commandFactory() {
        new DefaultCommandFactory()
    }

    @Primary
    @Bean
    Projector defaultProjector(Stage stage) {
        new DefaultProjector(stage)
    }

    @Bean
    ProjectionService projectionService(BeanFactory beanFactory, Projector arrangement) {
        new DefaultProjectionService(beanFactory, arrangement)
    }
}
