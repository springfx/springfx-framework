package org.springfx.iconfonts

import javafx.scene.text.Text
import org.springframework.core.io.Resource
import org.springfx.text.CachedFontSource
import org.springfx.text.FontSource
import org.springfx.text.ResourceFontSource

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class GenericIconFont implements IconFont {

    final Properties mapping
    final Resource fontLocation

    private FontSource fontProvider

    GenericIconFont(Properties mapping, FontSource fontProvider) {
        this.mapping = mapping
        this.fontLocation = fontLocation

        if (!CachedFontSource.isAssignableFrom(fontProvider?.class)) {
            fontProvider = new CachedFontSource(fontProvider)
        }
        this.fontProvider = fontProvider
    }

    GenericIconFont(Properties mapping, Resource fontLocation) {
        this(mapping, new ResourceFontSource(fontLocation))
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
