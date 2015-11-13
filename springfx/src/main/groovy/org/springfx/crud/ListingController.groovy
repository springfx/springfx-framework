package org.springfx.crud

import javafx.scene.control.TableView
import org.springfx.Controller
import org.springfx.crud.scene.ListingProjection
import org.springfx.model.ListingModel

/**
 *
 * @author Stephan Grundner
 */
interface ListingController<T, P extends ListingProjection<T, TableView<T>>> extends Controller<P> {

    ListingModel getModel()
}
