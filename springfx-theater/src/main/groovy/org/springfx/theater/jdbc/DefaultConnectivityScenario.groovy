package org.springfx.theater.jdbc

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ButtonBar
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import org.springfx.control.DataSourcePane
import org.springfx.theater.AbstractScenario

import javax.annotation.PostConstruct

/**
 *
 * @author Stephan Grundner
 */
class DefaultConnectivityScenario extends AbstractScenario implements ConnectivityScenario {

    private final ObjectProperty<EventHandler<ActionEvent>> onConnect = new SimpleObjectProperty<>(this, 'onConnect')

    DataSourcePane dataSourcePane

    DefaultConnectivityScenario(Stage stage) {
        super(stage)
    }

    @Override
    ObjectProperty<EventHandler<ActionEvent>> onConnectProperty() {
        onConnect
    }

    @Override
    EventHandler<ActionEvent> getOnConnect() {
        onConnectProperty().get()
    }

    @Override
    void setOnConnect(EventHandler onConnect) {
        onConnectProperty().set(onConnect)
    }

    @PostConstruct
    void init() {
        def root = new VBox()
        dataSourcePane = new DataSourcePane()
        dataSourcePane.dataBinder.addValidators(new Validator() {
            @Override
            boolean supports(Class<?> clazz) {
                true
            }

            @Override
            void validate(Object target, Errors errors) {
                def dataSourcePane = (DataSourcePane) target
                if (dataSourcePane.url.length() < 3) {
                    errors.rejectValue('url', 'min.three', 'Minimum of 3 characters!')
                }
            }
        })

        root.children.add(dataSourcePane)

        def buttonBar = new ButtonBar()
        Button yesButton = new Button("Connect");
        ButtonBar.setButtonData(yesButton, ButtonBar.ButtonData.CANCEL_CLOSE);
        yesButton.onAction = new EventHandler<ActionEvent>() {
            @Override
            void handle(ActionEvent event) {
                println "OK"
                dataSourcePane.dataBinder.validate()
                println dataSourcePane.dataBinder.bindingResult
            }
        }
        buttonBar.buttons.add(yesButton)
        root.children.add(buttonBar)


        scene = new Scene(root, 800, 600)
    }
}
