package org.springfx.iconfonts

import javafx.scene.text.Text

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface VectorIconFont extends IconFont {

    Text getIcon(String name)
}