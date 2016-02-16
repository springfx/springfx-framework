package org.springfx.command

import javafx.concurrent.Task

/**
 *
 * @author Stephan Grundner
 */
abstract class AbstractCommand<T> extends Task<T> implements Command<T> {

    @Override
    final void execute() {
        run()
    }
}
