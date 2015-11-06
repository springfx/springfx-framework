package org.springfx.theater.stage

import org.springfx.theater.scene.Scenario

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Inherited
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@interface Area {

    Class<? extends Scenario>[] scenario() default [Scenario]
    String value() default 'primary'
}