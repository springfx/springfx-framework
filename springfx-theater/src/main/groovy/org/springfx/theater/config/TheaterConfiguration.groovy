package org.springfx.theater.config

import javafx.stage.Stage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springfx.theater.stage.Arrangement
import org.springfx.theater.stage.SimpleBorderArrangement

/**
 *
 * @author Stephan Grundner
 */
@Configuration
class TheaterConfiguration {

    @Bean
    @Primary
    Arrangement arrangement(Stage stage) {
        new SimpleBorderArrangement(stage)
    }
}
