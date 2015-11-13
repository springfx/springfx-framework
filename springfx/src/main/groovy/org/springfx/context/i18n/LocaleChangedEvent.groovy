package org.springfx.context.i18n

import org.springframework.context.ApplicationEvent

/**
 * Event needs to get published after setting a locale on {@link org.springframework.context.i18n.LocaleContextHolder}.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class LocaleChangedEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     * @param source the object on which the event initially occurred (never {@code null})
     */
    LocaleChangedEvent(Object source) {
        super(source)
    }
}
