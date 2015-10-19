package org.springfx.samples.books

import javafx.application.Platform
import javafx.stage.Stage

/**
 *
 * @author grs
 */
class Application extends javafx.application.Application {

    @Override
    void start(Stage primaryStage) {
        Platform.setImplicitExit(true)

        primaryStage.show()
    }
}
