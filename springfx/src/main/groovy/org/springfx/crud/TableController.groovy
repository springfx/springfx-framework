package org.springfx.crud

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import org.springfx.command.CommandFactory
import org.springfx.command.CreateItemCommand
import org.springfx.crud.scene.SimpleTableProjection
import org.springfx.model.ListingModel
import org.springfx.model.ObjectListingModel

/**
 *
 * @author Stephan Grundner
 */
class TableController<T, P extends SimpleTableProjection<T>> {

    final ListingModel model
    final P projection

    protected CommandFactory commandFactory

    TableController(P projection, CommandFactory commandFactory, ListingModel model) {
        this.projection = projection
        this.model = model
        this.commandFactory = commandFactory
    }

    TableController(P projection, CommandFactory commandFactory) {
        this(projection, commandFactory, new ObjectListingModel())

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
        def command = commandFactory.getCommand(CreateItemCommand)
        command.execute()
    }
}
