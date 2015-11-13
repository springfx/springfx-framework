package org.springfx.validation

import javafx.beans.property.ObjectProperty
import javafx.beans.property.Property
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.Control
import org.springframework.validation.DataBinder
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springfx.beans.property.ControlPropertyResolver

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class ControlDataBinder<T> {

    private DataBinder dataBinder

    final Class<T> targetClass
    private final ControlPropertyResolver controlPropertyResolver

    private final ObjectProperty<T> targetProperty = new SimpleObjectProperty(this, 'target') {
        @Override
        void set(Object newValue) {
            super.set(newValue)
            dataBinder = new DataBinder(newValue)
        }
    }

    private final Map<String, Property<?>> propertyByField = [:]
    private final Set<ValidationHandler> validationHandlers = new HashSet<>()

    ControlDataBinder(Class<T> targetClass, ControlPropertyResolver controlPropertyResolver) {
        this.targetClass = targetClass
        this.controlPropertyResolver = controlPropertyResolver
    }

    ObjectProperty<T> targetProperty() {
        targetProperty
    }

    T getTarget() {
        targetProperty.get()
    }

    void setTarget(T target) {
        targetProperty.set(target)
    }

    Set<String> getBoundFields() {
        propertyByField.keySet()
    }

    boolean addValidationHandler(ValidationHandler errorHandler) {
        validationHandlers.add(errorHandler)
    }

    boolean removeValidationHandler(ValidationHandler errorHandler) {
        validationHandlers.remove(errorHandler)
    }

    void setValidationHandler(ValidationHandler validationHandler) {
        validationHandlers.clear()
        assert validationHandlers.add(validationHandler)
    }

    def void bind(Property controlValueProperty, String field) {
//        assertControlValueProperty(controlValueProperty)
        propertyByField.put(field, controlValueProperty)
    }

    final def void bind(Control control, String controlPropertyName, String field) {
        def controlValueProperty = controlPropertyResolver.getProperty(control, controlPropertyName)
        bind(controlValueProperty, field)
    }

    final def void bind(Control control, String field) {
        def controlProperty = controlPropertyResolver.getPrimaryProperty(control)
        bind(controlProperty, field)
    }

    void validate() {
        dataBinder.validate()
        dataBinder.bindingResult.with {
            globalErrors.each { ObjectError objectError ->
                validationHandlers*.onGlobalError(objectError)
            }

            propertyByField.each { field, controlValueProperty ->
                if (hasFieldErrors(field)) {
                    getFieldErrors(field).each { FieldError fieldError ->
                        if (controlValueProperty) {
                            validationHandlers*.onFieldError(fieldError, controlValueProperty)
                        }
                    }
                } else {
                    validationHandlers*.onFieldSuccess(controlValueProperty)
                }
            }
        }
    }
}
