package org.springfx.iconfonts

import javafx.scene.text.Text

/**
 *
 * @author Stephan Grundner
 */
interface IconFont {

    Properties getMapping()
    Text getIcon(String name, double size)
}
