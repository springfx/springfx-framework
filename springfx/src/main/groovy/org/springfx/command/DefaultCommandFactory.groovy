package org.springfx.command

/**
 *
 * @author Stephan Grundner
 */
class DefaultCommandFactory implements CommandFactory {

    @Override
    Command getCommand(Class<? extends Command> commandClass) {
        commandClass.newInstance()
    }
}
