package org.springfx.iconfonts

import javafx.beans.binding.Bindings
import javafx.beans.value.ObservableValueBase
import javafx.scene.text.Font
import javafx.scene.text.Text

/**
 *
 * @author Stephan Grundner
 */
final class Icons {

    static Text getIcon(Font font, String symbol) {
        def icon = new Text(symbol)
//        icon.setFont(font)
        icon.fontProperty().bind(new ObservableValueBase<Font>() {
            @Override
            Font getValue() {
                font
            }
        })
        icon
    }

    static Text getIcon(Font font, Properties mappings, String name) {
        getIcon(font, mappings.getProperty(name))
    }

    private Icons() {}
}
