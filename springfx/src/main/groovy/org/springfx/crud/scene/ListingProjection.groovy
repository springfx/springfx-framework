package org.springfx.crud.scene

import javafx.scene.control.Control
import org.springfx.scene.Projection

/**
 *
 * @author Stephan Grundner
 */
interface ListingProjection<T, L extends Control> extends Projection {

    static final CREATE_BUTTON_SOURCE = 'create-button'

    L getListing()
}