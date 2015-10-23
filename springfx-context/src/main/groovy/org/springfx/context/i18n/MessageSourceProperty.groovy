package org.springfx.context.i18n

import com.sun.javafx.binding.ExpressionHelper
import com.sun.javafx.collections.ObservableListWrapper
import javafx.beans.InvalidationListener
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.property.ReadOnlyStringProperty
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import org.springframework.context.MessageSource
import org.springfx.context.ApplicationContextUtils

/**
 *
 * @author Stephan Grundner
 */
class MessageSourceProperty extends ReadOnlyStringProperty {

    final MessageSource messageSource
    final String code
    private final ReadOnlyProperty<Locale> localeProperty

    final ObservableList<Object> args

    ExpressionHelper<String> helper

    MessageSourceProperty(String code, ObservableList<Object> args, MessageSource messageSource, ReadOnlyProperty<Locale> localeProperty) {
        this.code = code
        this.args = args
        this.messageSource = messageSource
        this.localeProperty = localeProperty

        localeProperty.addListener(new ChangeListener<Locale>() {
            @Override
            void changed(ObservableValue<? extends Locale> observable, Locale oldValue, Locale newValue) {
                fireValueChangedEvent()
            }
        })
        args.addListener(new ListChangeListener<Object>() {
            @Override
            void onChanged(ListChangeListener.Change<?> change) {
                fireValueChangedEvent()
            }
        })
    }

    MessageSourceProperty(String code, ObservableList<Object> args, MessageSource messageSource, LocalePropertyHolder localePropertyHolder) {
        this(code, args, messageSource, localePropertyHolder.localeProperty())
    }

    MessageSourceProperty(String code, ObservableList<Object> args, LocalePropertyHolder localePropertyHolder) {
        this(code, args, ApplicationContextUtils.messageSource, localePropertyHolder)
    }

    MessageSourceProperty(String code, ObservableList<Object> args) {
        this(code, args, ApplicationContextUtils.localePropertyHolder)
    }

    MessageSourceProperty(String code) {
        this(code, new ObservableListWrapper<>(new ArrayList<Object>()))
    }

    @Override
    String get() {
        messageSource.getMessage(code, args.toArray(), localeProperty.value)
    }

    @Override
    public void addListener(InvalidationListener listener) {
        helper = ExpressionHelper.addListener(helper, this, listener)
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        helper = ExpressionHelper.removeListener(helper, listener)
    }

    @Override
    public void addListener(ChangeListener<? super String> listener) {
        helper = ExpressionHelper.addListener(helper, this, listener)
    }

    @Override
    public void removeListener(ChangeListener<? super String> listener) {
        helper = ExpressionHelper.removeListener(helper, listener)
    }

    /**
     * Sends notifications to all attached
     * {@link javafx.beans.InvalidationListener InvalidationListeners} and
     * {@link javafx.beans.value.ChangeListener ChangeListeners}.
     *
     * This method needs to be called, if the value of this property changes.
     */
    protected void fireValueChangedEvent() {
        ExpressionHelper.fireValueChangedEvent(helper)
    }

    @Override
    Object getBean() { null }

    @Override
    String getName() { code }
}
