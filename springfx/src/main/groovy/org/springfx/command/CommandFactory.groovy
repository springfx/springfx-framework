package org.springfx.command

/**
 *
 * @author Stephan Grundner
 */
interface CommandFactory {

    Command getCommand(Class<? extends Command> commandClass)
}