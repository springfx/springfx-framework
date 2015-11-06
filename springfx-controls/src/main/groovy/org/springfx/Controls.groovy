package org.springfx

import javafx.scene.Node
import javafx.scene.control.*
import org.springframework.context.ApplicationContext
import org.springfx.context.ApplicationContextHolder
import org.springfx.context.ApplicationContextUtils
import org.springfx.control.FormPane

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
abstract class Controls {

    private static ApplicationContext applicationContext

    static ApplicationContext getApplicationContext() {
        applicationContext
    }

    static void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext
    }

    static def <T extends Node> T createControl(Class<T> controlType) {
        ApplicationContextUtils.getBeanOrInstance(applicationContext, controlType)
    }

    static FormPane createFormPane() {
        createControl(FormPane)
    }

    static Accordion createAccordion() {

    }

    static Button createButton() {
        createControl(Button)
    }

//    Cell?

    static CheckBox createCheckBox() {
        createControl(CheckBox)
    }

    static def <T> ChoiceBox<T> createChoiceBox() {
        createControl(ChoiceBox)
    }

    static ColorPicker createColorPicker() {
        createControl(ColorPicker)
    }

    static def <T> ComboBox<T> createComboBox() {
        createControl(ComboBox)
    }

    static ContextMenu createContextMenu() {
        createControl(ContextMenu)
    }

    static DatePicker createDatePicker() {
        createControl(DatePicker)
    }

    static Hyperlink createHyperlink() {
        createControl(Hyperlink)
    }

    static Label createLabel() {
        createControl(Label)
    }

    static def <T> ListView<T> createListView() {
        createControl(ListView)
    }

    static MenuBar createMenuBar() {
        createControl(MenuBar)
    }

    static MenuButton createMenuButton() {
        createControl(MenuButton)
    }

    static Pagination createPagination() {
        createControl(Pagination)
    }

    static PasswordField createPasswordField() {
        createControl(PasswordField)
    }

    static PopupControl createPopupControl() {
        createControl(PopupControl)
    }

    static ProgressBar createProgressBar() {
        createControl(ProgressBar)
    }

    static ProgressIndicator createProgressIndicator() {
        createControl(ProgressIndicator)
    }

    static RadioButton createRadioButton() {
        createControl(RadioButton)
    }

    static ScrollBar createScrollBar() {
        createControl(ScrollBar)
    }

    static ScrollPane createScrollPane() {
        createControl(ScrollPane)
    }

    static Separator createSeparator() {
        createControl(Separator)
    }
}
