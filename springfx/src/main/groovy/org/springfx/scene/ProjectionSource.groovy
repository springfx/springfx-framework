package org.springfx.scene

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Projection area.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
@Target([ElementType.FIELD, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface ProjectionSource {

    String[] value() default []
}