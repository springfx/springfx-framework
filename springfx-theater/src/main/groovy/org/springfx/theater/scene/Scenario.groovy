package org.springfx.theater.scene

import org.springfx.theater.stage.Arrangement

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
interface Scenario<T extends Arrangement> {

    void perform(T arrangement)
}
