package org.springfx.beans.property

import javafx.beans.property.Property
import javafx.beans.property.ReadOnlyProperty

/**
 *
 * @author Stephan Grundner
 */
final class PropertyUtils {

    public static boolean isPropertyName(String name) {
        Character.isLowerCase(name.charAt(0))
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
