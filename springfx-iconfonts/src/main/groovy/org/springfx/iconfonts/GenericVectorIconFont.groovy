package org.springfx.iconfonts

import javafx.scene.text.Font
import javafx.scene.text.Text
import org.springframework.core.io.Resource
import org.springfx.text.FontProvider
import org.springfx.text.ResourceFontProvider

/**
 *
 * @author Stephan Grundner
 */
class GenericVectorIconFont implements VectorIconFont {

    final Properties mapping
    final Resource fontLocation

    private FontProvider fontProvider

    GenericVectorIconFont(Properties mapping, FontProvider fontProvider) {
        this.mapping = mapping
        this.fontLocation = fontLocation
        this.fontProvider = fontProvider
    }

    GenericVectorIconFont(Properties mapping, Resource fontLocation) {
        this(mapping, new ResourceFontProvider(fontLocation))
    }

    @Override
    Text getIcon(String name) {
        getIcon(name, Font.default.size)
    }

    @Override
    Text getIcon(String name, double size) {
        assert size == Font.default.size
        def character = mapping.get(name) as String
        if (character != null) {
            def font = fontProvider.getFont(size)
            return Icons.getIcon(font, character)
        }
        null
    }
}
