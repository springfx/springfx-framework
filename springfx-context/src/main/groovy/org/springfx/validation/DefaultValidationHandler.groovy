package org.springfx.validation

import com.sun.javafx.css.StyleManager
import com.sun.javafx.css.Stylesheet
import javafx.application.Platform
import javafx.beans.property.Property
import javafx.scene.control.Control
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springfx.context.ApplicationContextHolder
import org.springfx.context.ApplicationContextUtils

/**
 * Default implementation for {@link ValidationHandler}.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class DefaultValidationHandler implements ValidationHandler {

    static final STYLESHEET_LOCATION = 'org/springfx/validation/errors.css'
    private static Stylesheet defaultErrorsStylesheet

    final ApplicationContext applicationContext

    DefaultValidationHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext
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
            def locale = ApplicationContextUtils.getLocalePropertyHolder(applicationContext).locale
            def text = applicationContext.getMessage(fieldError, locale)
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
