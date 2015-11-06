package org.springfx.theater.stage

import javafx.scene.Node

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface BorderArrangement extends Arrangement {

    Node getCenter()
    void setCenter(Node center)
}
