package org.springfx.beans.property

import javafx.beans.property.Property
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue

/**
 *
 * @author Stephan Grundner
 */
final class PropertyUtils {

    public static boolean isPropertyName(String name) {
        Character.isLowerCase(name.charAt(0))
    }

    static void onChange(ObservableValue<?> property, Closure<?> closure) {
        property.addListener(new ChangeListener<Object>() {
            @Override
            void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                assert observable == property
                def n = closure.maximumNumberOfParameters
                if (n == 3) {
                    closure.call(newValue, oldValue, property)
                } else if (n == 2) {
                    closure.call(newValue, property)
                } else {
                    closure.call(newValue)
                }
            }
        })
    }

    static ReadOnlyProperty<?> getReadOnlyProperty(Object bean, String name) {
        assert bean != null
        assert isPropertyName(name)
        Class<?> beanType = bean.getClass()
        def propertyMethod = beanType.getMethod("${name}Property")
        (ReadOnlyProperty<?>) propertyMethod.invoke(bean)
    }

    static Property<?> getProperty(Object bean, String name) {
        assert bean != null
        assert isPropertyName(name)
        Class<?> beanType = bean.getClass()
        def propertyMethod = beanType.getMethod("${name}Property")
        (Property<?>) propertyMethod.invoke(bean)
    }

    private PropertyUtils() {}
}
