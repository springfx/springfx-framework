package org.springfx.scene

/**
 * A projection.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface Projection {

    public static final String PRIMARY_SOURCE = "primary"

    Object getProjectionSource(String key)
}