package org.springfx.text

import javafx.scene.text.Font

/**
 *
 * @author Stephan Grundner
 */
interface FontProvider {

    Font getFont(double size)
}