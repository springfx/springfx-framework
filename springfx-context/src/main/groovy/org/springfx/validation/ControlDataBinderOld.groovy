package org.springfx.validation

import javafx.beans.property.Property
import javafx.scene.control.Control
import javafx.scene.control.TextField
import org.springframework.beans.PropertyValues
import org.springframework.validation.BindingResult
import org.springframework.validation.DataBinder
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springfx.beans.property.PropertyUtils

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class ControlDataBinderOld extends DataBinder {

    static enum AccessType {
        BEAN_PROPERTY_ACCESS,
        DIRECT_FIELD_ACCESS
    }

    private final Map<String, Property<?>> propertyByField = [:]
    private final Set<ValidationHandler> validationHandlers = new HashSet<>()

    private AccessType accessType

    ControlDataBinderOld(Object target, ValidationHandler validationHandler) {
        super(target)
        this.validationHandler = validationHandler
    }

    ControlDataBinderOld(Object target) {
        super(target)
    }

    Set<String> getBoundFields() {
        propertyByField.keySet()
    }

    @Override
    void initBeanPropertyAccess() {
        super.initBeanPropertyAccess()
        accessType = AccessType.BEAN_PROPERTY_ACCESS
    }

    @Override
    void initDirectFieldAccess() {
        super.initDirectFieldAccess()
        accessType = AccessType.DIRECT_FIELD_ACCESS
    }

    protected void resetBindingResult() {
        def bindingResultField = DataBinder.getDeclaredField('bindingResult')
        boolean wasAccessible = bindingResultField.accessible
        try {
            BindingResult bindingResult
            bindingResultField.accessible = true
            switch (accessType) {
                case AccessType.DIRECT_FIELD_ACCESS:
                    bindingResult = createDirectFieldBindingResult()
                    break
                case AccessType.BEAN_PROPERTY_ACCESS:
                default:
                    bindingResult = createBeanPropertyBindingResult()
            }
            bindingResultField.set(this, bindingResult)
        } finally {
            bindingResultField.accessible = wasAccessible
        }
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

    protected void assertControlValueProperty(Property<?> property) {
        assert property != null && property.bean != null
        assert Control.isAssignableFrom(property.bean.getClass())
    }

    def void bind(Property controlValueProperty, String field) {
        assertControlValueProperty(controlValueProperty)
//        def targetProperty = PropertyUtils.getProperty(target, field)
//        if (!targetProperty) {
//            throw new RuntimeException("No such property: $targetProperty")
//        }
//        targetProperty.bind(controlValueProperty)
        propertyByField.put(field, controlValueProperty)
    }

    final def void bind(Control control, String controlValuePropertyName, String field) {
        def controlValueProperty = PropertyUtils.getProperty(control, controlValuePropertyName)
        bind(controlValueProperty, field)
    }

    final def void bind(TextField control, String field) {
        bind(control, 'text', field)
    }

    @Override
    void bind(PropertyValues pvs) {
        throw new UnsupportedOperationException()
    }

    @Override
    void validate() {
        resetBindingResult()
        super.validate()
        bindingResult.with {
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

    @Override
    Map<?, ?> close() {
        propertyByField.values().each { property ->
            if (property.isBound()) {
                property.unbind()
            }
        }
        super.close()
    }
}
