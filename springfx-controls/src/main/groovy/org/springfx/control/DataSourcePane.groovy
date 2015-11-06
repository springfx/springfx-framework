package org.springfx.control

import javafx.beans.property.*
import javafx.geometry.Pos
import javafx.scene.control.Control
import javafx.scene.control.Skin
import org.springframework.context.ApplicationContext
import org.springfx.control.skin.DataSourcePaneSkin
import org.springfx.validation.DataBindingSupport

/**
 *
 * @author Stephan Grundner
 */
class DataSourcePane extends Control implements DataBindingSupport {

    private final ApplicationContext applicationContext

    private final ObjectProperty<Pos> alignment = new SimpleObjectProperty<>(this, 'alignment')
    private final StringProperty url = new SimpleStringProperty(this, 'url')
    private final StringProperty username = new SimpleStringProperty(this, 'username')
    private final StringProperty password = new SimpleStringProperty(this, 'password')
    private final BooleanProperty remember = new SimpleBooleanProperty(this, 'remember')

    DataSourcePane(ApplicationContext applicationContext) {
        assert applicationContext != null
        this.applicationContext = applicationContext
    }

    ObjectProperty<Pos> alignmentProperty() {
        alignment
    }

    Pos getAlignment() {
        alignmentProperty().get()
    }

    void setAlignment(Pos alignment) {
        alignmentProperty().set(alignment)
    }

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
        new DataSourcePaneSkin(this, applicationContext)
    }
}
