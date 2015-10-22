package org.springfx.control

import javafx.scene.control.Control
import javafx.scene.control.Skin

/**
 *
 * @author Stephan Grundner
 */
class MessagePane extends Control {
    
    @Override
    protected Skin<?> createDefaultSkin() {
        new MessagePaneSkin(this)
    }
}
