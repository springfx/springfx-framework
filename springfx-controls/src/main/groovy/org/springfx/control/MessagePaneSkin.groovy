package org.springfx.control

import javafx.scene.control.SkinBase

/**
 *
 * @author Stephan Grundner
 */
class MessagePaneSkin extends SkinBase<MessagePane> {

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    protected MessagePaneSkin(MessagePane control) {
        super(control)
    }
}
