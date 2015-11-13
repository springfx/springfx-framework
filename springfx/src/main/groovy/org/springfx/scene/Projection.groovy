package org.springfx.scene

/**
 * A view.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface Projection {

    public static final String PRIMARY_SOURCE = "primary"

    Object getProjectionSource(String key)
}