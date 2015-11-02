package org.springfx.theater.jdbc

import javafx.event.Event
import javafx.event.EventHandler
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springfx.control.DataSourcePane

/**
 *
 * @author Stephan Grundner
 */
class ConnectivityScenarioController {

    private final ConnectivityScenario connectivityScenario

    ConnectivityScenarioController(ConnectivityScenario connectivityScenario) {
        this.connectivityScenario = connectivityScenario

        connectivityScenario.setOnConnect(new EventHandler() {

            void handle(Event event) {
                println "OK"
            }
        })
    }

    DataSourcePane getDataSourcePane() {
        connectivityScenario.dataSourcePane
    }

    void installDataSource() {
        def dataSourcePane = connectivityScenario.dataSourcePane
        def dataSource = new DriverManagerDataSource()
        dataSource.setUrl(dataSourcePane.urlProperty().get())
        dataSource.setUsername(dataSourcePane.usernameProperty().get())
        dataSource.setPassword(dataSourcePane.passwordProperty().get())
    }
}
