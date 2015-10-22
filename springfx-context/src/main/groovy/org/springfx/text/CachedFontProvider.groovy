package org.springfx.text

import javafx.scene.text.Font

/**
 *
 * @author Stephan Grundner
 */
class CachedFontProvider implements FontProvider {

    static CachedFontProvider wrap(FontProvider fontProvider) {
        if (CachedFontProvider.isAssignableFrom(fontProvider?.getClass())) {
            return fontProvider
        }
        new CachedFontProvider(fontProvider)
    }

    private final Map<Double, Font> fontBySize = [:]
    private final FontProvider fontProvider

    CachedFontProvider(FontProvider fontProvider) {
        this.fontProvider = fontProvider
    }

    @Override
    Font getFont(double size) {
        def font = fontBySize.get(size)
        if (font == null) {
            font = fontProvider.getFont(size)
            fontBySize.put(size, font)
        }
        font
    }
}
