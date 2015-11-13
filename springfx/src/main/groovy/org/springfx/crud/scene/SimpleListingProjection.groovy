package org.springfx.crud.scene

import javafx.scene.control.Button
import javafx.scene.control.TableView
import org.springfx.scene.AnnotationConfigProjection
import org.springfx.scene.Projection
import org.springfx.scene.ProjectionSource

/**
 *
 * @author Stephan Grundner
 */
class SimpleListingProjection<T> extends AnnotationConfigProjection implements ListingProjection<T, TableView<T>> {

    TableView<T> listing
    Button createButton

    SimpleListingProjection() {
        listing = new TableView()
        createButton = new Button("New")
    }

    @ProjectionSource(Projection.PRIMARY_SOURCE)
    TableView<T> getListing() {
        return listing
    }

    @ProjectionSource(ListingProjection.CREATE_BUTTON_SOURCE)
    Button getCreateButton() {
        return createButton
    }
}
