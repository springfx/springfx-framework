package org.springfx.iconfonts

import javafx.scene.text.Text

/**
 *
 * @author Stephan Grundner
 */
interface VectorIconFont extends IconFont {

    Text getIcon(String name)
}