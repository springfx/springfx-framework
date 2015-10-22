package org.springfx.stage

import com.sun.javafx.stage.StageHelper
import javafx.collections.ObservableList
import javafx.stage.Stage
import org.springfx.context.ApplicationContextHolder
import org.springfx.context.ApplicationContextUtils

/**
 *
 * @author Stephan Grundner
 */
final class StageUtils {

    ObservableList<Stage> getStages() {
        StageHelper.getStages()
    }

    Stage getPrimaryStage() {
        def context = ApplicationContextHolder.context
        ApplicationContextUtils.getPrimaryStage(context)
    }

    private StageUtils() {}
}
