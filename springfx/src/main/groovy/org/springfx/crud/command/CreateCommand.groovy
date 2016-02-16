package org.springfx.crud.command

import org.springfx.command.AbstractCommand

/**
 *
 * @author Stephan Grundner
 */
class CreateCommand extends AbstractCommand<Void> {

    @Override
    protected Void call() throws Exception {

        println "juhu"
    }
}
