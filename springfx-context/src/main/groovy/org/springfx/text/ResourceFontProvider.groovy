package org.springfx.text

import javafx.scene.text.Font
import org.springframework.core.io.Resource

/**
 *
 * @author Stephan Grundner
 */
class ResourceFontProvider implements FontProvider {

    private final Resource resource

    ResourceFontProvider(Resource resource) {
        this.resource = resource
    }

    @Override
    Font getFont(Resource resource, double size) {
        def font = Font.loadFont(resource.inputStream, size)

        font
    }
}
