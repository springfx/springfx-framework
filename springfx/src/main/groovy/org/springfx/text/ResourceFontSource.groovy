package org.springfx.text

import javafx.scene.text.Font
import org.springframework.core.io.Resource

/**
 *
 * @author Stephan Grundner
 */
class ResourceFontSource implements FontSource {

    private final Resource resource

    ResourceFontSource(Resource resource) {
        this.resource = resource
    }

    @Override
    Font getFont(double size) {
        def font = Font.loadFont(resource.inputStream, size)

        font
    }
}
