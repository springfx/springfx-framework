package org.springfx.iconfonts

import javafx.scene.text.Text

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface IconFont {

    Properties getMapping()
    Text getIcon(String name, double size)
}
