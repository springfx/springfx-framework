package org.springfx.context

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
import org.springframework.context.MessageSourceResolvable
import org.springframework.util.StringUtils
import org.springfx.context.i18n.LocalePropertyHolder

/**
 *
 * @author Stephan Grundner
 */
class MessageProperty extends ReadOnlyStringProperty implements MessageSourceResolvable {

    final MessageSource messageSource
    final String code
    final String defaultMessage

    private final ReadOnlyProperty<Locale> localeProperty
    private final ObservableList<Object> argumentsProperty = new ObservableListWrapper<>([])

    ExpressionHelper<String> helper

    MessageProperty(String code, Object[] arguments, String defaultMessage, MessageSource messageSource, ReadOnlyProperty<Locale> localeProperty) {
        assert !StringUtils.isEmpty(code)
        this.code = code
        this.arguments = arguments
        this.defaultMessage = defaultMessage

        assert messageSource != null
        this.messageSource = messageSource
        this.localeProperty = localeProperty

        localeProperty.addListener(new ChangeListener<Locale>() {
            @Override
            void changed(ObservableValue<? extends Locale> observable, Locale oldValue, Locale newValue) {
                fireValueChangedEvent()
            }
        })
        argumentsProperty.addListener(new ListChangeListener<Object>() {
            @Override
            void onChanged(ListChangeListener.Change<?> change) {
                fireValueChangedEvent()
            }
        })
    }

    MessageProperty(String code, Object[] arguments, String defaultMessage, MessageSource messageSource, LocalePropertyHolder localePropertyHolder) {
        this(code, arguments, defaultMessage, messageSource, localePropertyHolder.localeProperty())
    }

    MessageProperty(String code, Object[] arguments, String defaultMessage, LocalePropertyHolder localePropertyHolder) {
        this(code, arguments, defaultMessage, ApplicationContextUtils.messageSource, localePropertyHolder)
    }

    MessageProperty(String code, Object[] arguments, String defaultMessage) {
        this(code, arguments, defaultMessage, ApplicationContextUtils.localePropertyHolder)
    }

    MessageProperty(String code, Object[] arguments) {
        this(code, arguments, (String) null)
    }

    MessageProperty(String code) {
        this(code, (String) null)
    }

    @Override
    String[] getCodes() {
        [code] as String[]
    }

    @Override
    Object[] getArguments() {
        argumentsProperty.toArray()
    }

    void setArguments(Object[] arguments) {
        argumentsProperty.clear()
        if (arguments != null) {
            argumentsProperty.addAll(arguments)
        }
    }

    ObservableList<Object> argumentsProperty() {
        argumentsProperty
    }

    @Override
    String get() {
        messageSource.getMessage(this, localeProperty.value)
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
