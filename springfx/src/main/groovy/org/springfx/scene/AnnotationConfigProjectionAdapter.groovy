package org.springfx.scene

import javafx.scene.Node

import java.beans.Introspector
import java.lang.reflect.Method

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class AnnotationConfigProjectionAdapter implements Projection {

    final Object projection

    Map<String, Method> readMethods = [:]

    AnnotationConfigProjectionAdapter(Object projection) {
        this.projection = projection

        def projectionClass = projection.getClass()
        def beanInfo = Introspector.getBeanInfo(projectionClass)
        beanInfo.propertyDescriptors.each { pd ->
            def m = pd.readMethod
            def a = m.getAnnotation(ProjectionSource)
            if (a) {
                a.value().each { key ->
                    readMethods.put(key, m)
                }
            }
        }
    }

    @Override
    Node getProjectionSource(String area) {
        (Node) readMethods.get(area)?.invoke(projection)
    }
}
