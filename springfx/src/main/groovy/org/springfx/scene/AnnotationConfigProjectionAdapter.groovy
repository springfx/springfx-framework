package org.springfx.scene

import javafx.scene.Node

import java.beans.BeanDescriptor
import java.beans.Introspector
import java.beans.PropertyDescriptor
import java.lang.annotation.Annotation
import java.lang.reflect.Method

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class AnnotationConfigProjectionAdapter implements Projection {

    final Object projection

    Map<String, Method> readMethods = [:]

    void registerMethod(String[] keys, Method m) {
        keys.each { key ->
            readMethods.put(key, m)
        }
    }

    private def <T extends Annotation> T getAnnotationForProperty(Class<T> annotationClass, BeanDescriptor beanDescriptor, PropertyDescriptor propertyDescriptor) {
        def method = propertyDescriptor.readMethod
        def annotation = method.getAnnotation(annotationClass)
        if (annotation == null) {
            def beanClass = beanDescriptor.getBeanClass()
            try {
                def field = beanClass.getDeclaredField(propertyDescriptor.name)
                if (field) {
                    annotation = field.getAnnotation(annotationClass)
                    return annotation
                }
            } catch (NoSuchFieldException e) {}
        }
        annotation
    }

    AnnotationConfigProjectionAdapter(Object projection) {
        this.projection = projection

        def projectionClass = projection.getClass()
        def beanInfo = Introspector.getBeanInfo(projectionClass)
        beanInfo.propertyDescriptors.each { pd ->
            def m = pd.readMethod
            def a = m.getAnnotation(ProjectionSource)
            if (a) {
                registerMethod(a.value(), m)
            } else {
                a = getAnnotationForProperty(ProjectionSource, beanInfo.beanDescriptor, pd)
                if (a) {
                    registerMethod(a.value(), m)
                }
            }
        }
    }

    @Override
    Node getProjectionSource(String area) {
        (Node) readMethods.get(area)?.invoke(projection)
    }
}
