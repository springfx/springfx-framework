package org.springfx.control.skin

import javafx.scene.control.*
import org.springframework.context.ApplicationContext
import org.springfx.context.ApplicationContextUtils
import org.springfx.context.MessageProperty
import org.springfx.control.DataSourcePane
import org.springfx.control.FormPane
import org.springfx.Controls

/**
 *
 * @author Stephan Grundner
 */
class DataSourcePaneSkin extends SkinBase<DataSourcePane> {

    FormPane formPane
    final ApplicationContext applicationContext

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    DataSourcePaneSkin(DataSourcePane control, ApplicationContext applicationContext) {
        super(control)
        this.applicationContext = applicationContext
        Controls.applicationContext = applicationContext
        formPane = Controls.createFormPane()
        def dataBinder = skinnable.dataBinder
        def urlField = new TextField()
        dataBinder.bind(urlField, 'url')
        def urlLabel = new Label()
        urlLabel.textProperty().bind(new MessageProperty('dataSourcePane.url.label', null, 'URL',
                applicationContext))
        urlLabel.labelFor = urlField
        formPane.add(urlLabel, urlField, 0, 0)

        def usernameField = new TextField()
        dataBinder.bind(usernameField, 'username')
        def usernameLabel = new Label()
        usernameLabel.textProperty().bind(new MessageProperty('dataSourcePane.username.label', null, 'Username',
                applicationContext))
        usernameLabel.labelFor = usernameField
        formPane.add(usernameLabel, usernameField, 0, 1)

        def passwordField = new PasswordField()
        dataBinder.bind(passwordField, 'password')
        def passwordLabel = new Label()
        passwordLabel.textProperty().bind(new MessageProperty('dataSourcePane.password.label', null, 'Password',
                applicationContext))
        passwordLabel.labelFor = passwordField
        formPane.add(passwordLabel, passwordField, 0, 2)

        def rememberField = new CheckBox()
        dataBinder.bind(rememberField.selectedProperty(), 'remember')
        rememberField.textProperty().bind(new MessageProperty('dataSourcePane.remember.label', null, 'Remember',
                applicationContext))
        formPane.add(null, rememberField, 0, 3)
        rememberField.textProperty()
        children.add(formPane)

        formPane.alignmentProperty().bind(skinnable.alignmentProperty())
    }
}
