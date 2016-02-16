package org.springfx.crud

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import org.springfx.command.CommandFactory
import org.springfx.crud.command.CreateCommand
import org.springfx.crud.scene.TableProjection
import org.springfx.model.ListModel
import org.springfx.model.ObjectListingModel

/**
 *
 * @author Stephan Grundner
 */
class TableController<T, P extends TableProjection<T>> {

    final CommandFactory commandFactory
    final P projection
    final ListModel model

    TableController(CommandFactory commandFactory, P projection, ListModel model) {
        this.commandFactory = commandFactory
        this.projection = projection
        this.model = model
    }

    TableController(CommandFactory commandFactory, P projection) {
        this(commandFactory, projection, new ObjectListingModel())

        def createButton = projection.getProjectionSource('create-button')
        if (createButton instanceof Button) {
            createButton.onAction = new EventHandler<ActionEvent>() {
                @Override
                void handle(ActionEvent event) {
                    create()
                }
            }
        }
    }

    void create() {
        commandFactory.getCommand(CreateCommand).execute()
    }
}
