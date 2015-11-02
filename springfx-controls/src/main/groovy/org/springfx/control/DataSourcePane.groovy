package org.springfx.control

import javafx.beans.property.BooleanProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.scene.control.Control
import javafx.scene.control.Skin
import org.springfx.control.skin.DataSourcePaneSkin
import org.springfx.validation.DataBindingSupport

/**
 *
 * @author Stephan Grundner
 */
class DataSourcePane extends Control implements DataBindingSupport {

    private final StringProperty url = new SimpleStringProperty(this, 'url')
    private final StringProperty username = new SimpleStringProperty(this, 'username')
    private final StringProperty password = new SimpleStringProperty(this, 'password')
    private final BooleanProperty remember = new SimpleBooleanProperty(this, 'remember')

    StringProperty urlProperty() {
        url
    }

    String getUrl() {
        urlProperty().get()
    }

    void setUrl(String url) {
        urlProperty().set(url)
    }

    StringProperty usernameProperty() {
        username
    }

    String getUsername() {
        usernameProperty().get()
    }

    void setUsername(String username) {
        usernameProperty().set(username)
    }

    StringProperty passwordProperty() {
        password
    }

    String getPassword() {
        passwordProperty().get()
    }

    void setPassword(String password) {
        passwordProperty().set(password)
    }

    BooleanProperty rememberProperty() {
        remember
    }

    Boolean isRemember() {
        rememberProperty().get()
    }

    void setRemember(Boolean remember) {
        rememberProperty().set(remember)
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        new DataSourcePaneSkin(this)
    }
}
