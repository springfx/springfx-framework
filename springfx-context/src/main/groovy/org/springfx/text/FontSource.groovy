package org.springfx.text

import javafx.scene.text.Font

/**
 *
 * @author Stephan Grundner
 */
interface FontSource {

    Font getFont(double size)
}