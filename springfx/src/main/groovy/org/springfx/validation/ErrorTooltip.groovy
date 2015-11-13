package org.springfx.validation

import javafx.scene.control.Tooltip

/**
 *
 * @author Stephan Grundner
 */
class ErrorTooltip extends Tooltip {

    final Tooltip original


    ErrorTooltip(Tooltip original) {
        this.original = original
    }

    ErrorTooltip(String text) {
        super(text)
    }
}
