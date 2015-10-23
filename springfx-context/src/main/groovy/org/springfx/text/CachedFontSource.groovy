package org.springfx.text

import javafx.scene.text.Font

/**
 *
 * @author Stephan Grundner
 */
class CachedFontSource implements FontSource {

    static CachedFontSource wrap(FontSource fontSource) {
        if (CachedFontSource.isAssignableFrom(fontSource?.getClass())) {
            return fontSource
        }
        new CachedFontSource(fontSource)
    }

    private final Map<Double, Font> fontBySize = [:]
    private final FontSource fontSource

    CachedFontSource(FontSource fontSource) {
        this.fontSource = fontSource
    }

    @Override
    Font getFont(double size) {
        def font = fontBySize.get(size)
        if (font == null) {
            font = fontSource.getFont(size)
            fontBySize.put(size, font)
        }
        font
    }
}
