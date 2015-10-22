package org.springfx.validation
/**
 *
 * @author Stephan Grundner
 */
final class ValidationUtils {

    static void clearErrorStyleClass(javafx.scene.Node node) {
        ObservableList<String> styleClass = node.styleClass
        styleClass.remove('error')
    }

    static void applyErrorStyleClass(javafx.scene.Node node) {
        ObservableList<String> styleClass = node.styleClass
        if (!styleClass.contains('error')) {
            styleClass.add("error")
        }
    }

    private ValidationUtils() {}
}
