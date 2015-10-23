package org.springfx.context.i18n

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import org.springframework.context.i18n.LocaleContext

/**
 * Holds the {@link Locale} instance for an application.
 *
 * Don't use the {@link org.springframework.context.i18n.LocaleContextHolder} class directly in
 * an JavaFX application! Use this class instead.
 *
 * The singleton instance is available on every bound {@link org.springframework.context.ApplicationContext}.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class LocalePropertyHolder implements LocaleContext {

    private final localeProperty = new SimpleObjectProperty<Locale>()

    ObjectProperty<Locale> localeProperty() {
        localeProperty
    }

    Locale getLocale() {
        localeProperty.get()
    }

    void setLocale(Locale locale) {
        localeProperty.set(locale)
    }
}
