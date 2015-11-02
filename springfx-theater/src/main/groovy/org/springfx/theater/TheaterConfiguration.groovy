package org.springfx.theater

import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springfx.theater.jdbc.ConnectivityScenario
import org.springfx.theater.jdbc.DefaultConnectivityScenario

/**
 *
 * @author Stephan Grundner
 */
@Configuration
@EnableAutoConfiguration(exclude = [
        DataSourceAutoConfiguration,
        HibernateJpaAutoConfiguration,
])
class TheaterConfiguration {

    @Bean
    @Autowired
    ConnectivityScenario connectivityScenario(@Qualifier('primaryStage') Stage stage) {
        new DefaultConnectivityScenario(stage)
    }

//    @Bean
//    DataSource dataSource() {
//        DataSourceBuilder.create()
//                .driverClassName('org.h2.Driver')
//                .username('sa')
//                .url('jdbc:h2:mem:AZ;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE')
//                .build()
//    }
}
