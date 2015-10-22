package org.springfx.text

import javafx.scene.text.Font
import org.springframework.core.io.Resource

/**
 *
 * @author Stephan Grundner
 */
interface FontProvider {

    Font getFont(Resource resource, double size)
}