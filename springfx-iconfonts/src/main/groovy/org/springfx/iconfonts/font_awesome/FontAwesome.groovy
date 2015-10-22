package org.springfx.iconfonts.font_awesome

import groovy.json.StringEscapeUtils
import javafx.scene.text.Font
import javafx.scene.text.Text
import org.springframework.context.ApplicationContext
import org.springframework.core.io.Resource
import org.springfx.iconfonts.IconFont
import org.springfx.iconfonts.Icons

import java.util.regex.Pattern

/**
 *
 * @author Stephan Grundner
 */
class FontAwesome implements IconFont {

    static enum FontType {
        TTF('ttf'),
        WOFF('woff');

        final String extension

        FontType(String extension) {
            this.extension = extension
        }
    }

    private static final PATTERN = Pattern.compile(/[\$](.+)[:].*"\\f([0-9]+)"/)

    private final ApplicationContext context
    final String version
    final FontType fontType

    private Font font

    private Properties mappings

    FontAwesome(ApplicationContext context, String version, FontType fontType) {
        this.context = context
        this.version = version
        this.fontType = fontType
    }

    FontAwesome(ApplicationContext context, String version) {
        this(context, version, FontType.TTF)
    }

    protected String getBaseLocation() {
        "META-INF/resources/webjars/font-awesome/${version}"
    }

    protected String getVariablesScssLocation() {
        "$baseLocation/scss/_variables.scss"
    }

    protected Properties loadMappings() {
        def properties = new Properties()
        def location = getVariablesScssLocation()
        def resource = context.getResource(location)
        def scanner = new Scanner(resource.inputStream)
        try {
            while (scanner.hasNext()) {
                def line = scanner.nextLine()
                def m = PATTERN.matcher(line)
                if (m.find()) {
                    def key = m.group(1)
                    key = key.replace('-var', '')
                    def value = m.group(2)
//                    TODO Clean this up:
                    value = StringEscapeUtils.unescapeJava("\\uf$value")
                    properties.put(key, value)
                }
            }
        } finally {
            scanner.close()
        }
        properties
    }

    Properties getMappings() {
        if (mappings == null) {
            mappings = loadMappings()
        }
        mappings
    }

    protected String getFontLocation() {
        "$baseLocation/fonts/fontawesome-webfont.${fontType.extension}"
    }

    @Override
    Resource getFontResource() {
        context.getResource(fontLocation)
    }

    @Override
    Text getIcon(String name) {
        if (font == null) {
            def size = Font.default.size
            font = Font.loadFont(fontResource.inputStream, size)
        }
        Icons.getIcon(font, getMappings(), name)
    }
}
