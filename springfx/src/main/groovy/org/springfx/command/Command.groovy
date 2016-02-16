package org.springfx.command

import javafx.concurrent.Worker

/**
 *
 * @author Stephan Grundner
 */
interface Command<T> extends Worker<T> {

    void execute()
}