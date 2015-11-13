package org.springfx.validation

import com.sun.javafx.css.StyleManager
import com.sun.javafx.css.Stylesheet
import javafx.application.Platform
import javafx.beans.property.Property
import javafx.scene.control.Control
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError

/**
 * Default implementation for {@link ValidationHandler}.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class DefaultValidationHandler implements ValidationHandler {

    static final STYLESHEET_LOCATION = 'org/springfx/validation/errors.css'
    private static Stylesheet defaultErrorsStylesheet

    final MessageSource messageSource

    DefaultValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource
    }

    @Override
    void onGlobalError(ObjectError objectError) { }

    protected void ensureStylesheetLoaded() {
        assert Platform.isFxApplicationThread()
        if (defaultErrorsStylesheet == null) {
            def styleManager = StyleManager.getInstance()
            defaultErrorsStylesheet = styleManager.loadStylesheet(STYLESHEET_LOCATION)
            styleManager.addUserAgentStylesheet(null, defaultErrorsStylesheet)
        }
    }

    @Override
    void onFieldError(FieldError fieldError, Property<?> controlValueProperty) {
        def control = controlValueProperty.bean as Control
        if (!control.styleClass.contains('error')) {
            control.styleClass.add('error')
            ensureStylesheetLoaded()

            def tooltip = control.tooltip
            if (!(tooltip instanceof ErrorTooltip)) {
                tooltip = new ErrorTooltip(tooltip)
            }

            def locale = LocaleContextHolder.locale
            def text = messageSource.getMessage(fieldError, locale)
            tooltip.text = text
            control.setTooltip(tooltip)
        }
    }

    @Override
    void onFieldSuccess(Property<?> controlValueProperty) {
        def control = controlValueProperty.bean as Control
        control.styleClass.remove('error')
        def tooltip = control.tooltip
        if (tooltip instanceof ErrorTooltip) {
            control.tooltip = ((ErrorTooltip) tooltip).original
        } else {
            control.tooltip = null
        }
    }
}
