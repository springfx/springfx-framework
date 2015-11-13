package org.springfx.crud

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import org.springfx.command.CommandFactory
import org.springfx.command.CreateItemCommand
import org.springfx.crud.scene.ListingProjection
import org.springfx.model.ListingModel
import org.springfx.model.ObjectListingModel

/**
 *
 * @author Stephan Grundner
 */
class SimpleListingController<T, P extends ListingProjection<T, ?>> implements ListingController<T, P> {

    final ListingModel model
    final P projection

    protected CommandFactory commandFactory

    SimpleListingController(P projection, CommandFactory commandFactory, ListingModel model) {
        this.projection = projection
        this.model = model
        this.commandFactory = commandFactory
    }

    SimpleListingController(P projection, CommandFactory commandFactory) {
        this(projection, commandFactory, new ObjectListingModel())

        def createButton = projection.getProjectionSource(ListingProjection.CREATE_BUTTON_SOURCE)
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
