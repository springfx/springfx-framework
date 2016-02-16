package org.springfx.scene

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
abstract class AnnotationConfigProjection extends AbstractProjection {

    private AnnotationConfigProjectionAdapter adapter

    @Override
    Object getProjectionSource(String key) {
        if (adapter == null) {
            adapter = new AnnotationConfigProjectionAdapter(this)
        }
        adapter.getProjectionSource(key)
    }
}
