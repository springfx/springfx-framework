package org.springfx.theater.jdbc

import javafx.beans.property.ObjectProperty
import javafx.event.ActionEvent
import javafx.event.EventHandler
import org.springfx.control.DataSourcePane
import org.springfx.theater.Scenario

/**
 *
 * @author Stephan Grundner
 */
interface ConnectivityScenario extends Scenario {

    ObjectProperty<EventHandler<ActionEvent>> onConnectProperty()
    EventHandler<ActionEvent> getOnConnect()
    void setOnConnect(EventHandler<ActionEvent> onConnect)

    DataSourcePane getDataSourcePane()
}