package org.springfx.iconfonts

import javafx.scene.text.Text
import org.springframework.core.io.Resource
import org.springfx.text.CachedFontProvider
import org.springfx.text.FontProvider
import org.springfx.text.ResourceFontProvider

/**
 *
 * @author Stephan Grundner
 */
class GenericIconFont implements IconFont {

    final Properties mapping
    final Resource fontLocation

    private FontProvider fontProvider

    GenericIconFont(Properties mapping, FontProvider fontProvider) {
        this.mapping = mapping
        this.fontLocation = fontLocation

        if (!CachedFontProvider.isAssignableFrom(fontProvider?.class)) {
            fontProvider = new CachedFontProvider(fontProvider)
        }
        this.fontProvider = fontProvider
    }

    GenericIconFont(Properties mapping, Resource fontLocation) {
        this(mapping, new ResourceFontProvider(fontLocation))
    }

    @Override
    Text getIcon(String name, double size) {
        def character = mapping.get(name) as String
        if (character != null) {
            def font = fontProvider.getFont(size)
            return Icons.getIcon(font, character)
        }
        null
    }
}
