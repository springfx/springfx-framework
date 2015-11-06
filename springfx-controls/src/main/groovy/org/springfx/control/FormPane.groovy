package org.springfx.control

import javafx.beans.InvalidationListener
import javafx.beans.Observable
import javafx.beans.property.IntegerProperty
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.Control
import javafx.scene.control.Labeled
import javafx.scene.control.Skin
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.Priority
import org.springfx.control.skin.FormPaneSkin

/**
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class FormPane extends Control {

    private static void setControlProperty(Node node, Object key, Object value) {
        if (value == null) {
            node.getProperties().remove(key)
        } else {
            node.getProperties().put(key, value)
        }
        if (node.parent) {
            node.parent?.requestLayout()
        }
    }

    private static Object getControlProperty(Node node, Object key) {
        if (node.hasProperties()) {
            Object value = node.properties[key]
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    private static final LABEL_PROPERTY_KEY = new Object()
    private static final CONSTRAINTS_PROPERTY_KEY = new Object()

    static final class Constraints {

        private final IntegerProperty columnIndex = new SimpleIntegerProperty(this, 'columnIndex')
        private final IntegerProperty rowIndex = new SimpleIntegerProperty(this, 'rowIndex')
        private final IntegerProperty colspan = new SimpleIntegerProperty(this, 'colspan')
        private final IntegerProperty rowspan = new SimpleIntegerProperty(this, 'rowspan')
        private final IntegerProperty columnPercentWidth = new SimpleIntegerProperty(this, 'columnPercentWidth')

        Constraints(int columnIndex, int rowIndex, int colspan, int rowspan) {
            columnIndexProperty().set(columnIndex)
            rowIndexProperty().set(rowIndex)
            colspanProperty().set(colspan)
            rowspanProperty().set(rowspan)
        }

        Constraints(int columnIndex, int rowIndex) {
            columnIndexProperty().set(columnIndex)
            rowIndexProperty().set(rowIndex)
        }

        IntegerProperty columnIndexProperty() {
            columnIndex
        }

        Integer getColumnIndex() {
            columnIndexProperty().get()
        }

        void setColumnIndex(Integer columnIndex) {
            columnIndexProperty().set(columnIndex)
        }

        IntegerProperty rowIndexProperty() {
            rowIndex
        }

        Integer getRowIndex() {
            rowIndexProperty().get()
        }

        void setRowIndex(Integer rowIndex) {
            rowIndexProperty().set(rowIndex)
        }

        IntegerProperty colspanProperty() {
            colspan
        }

        Integer getColspan() {
            colspanProperty().get()
        }

        void setColspan(Integer colspan) {
            colspanProperty().set(colspan)
        }

        IntegerProperty rowspanProperty() {
            rowspan
        }

        Integer getRowspan() {
            rowspanProperty().get()
        }

        void setRowspan(Integer rowspan) {
            rowspanProperty().set(rowspan)
        }
    }

    private static setLabel(Node node, Labeled labeled) {
        setControlProperty(node, LABEL_PROPERTY_KEY, labeled)
    }

    static Labeled getLabel(Node node) {
        (Labeled) getControlProperty(node, LABEL_PROPERTY_KEY)
    }

    private static setConstraints(Node node, Constraints constraints) {
        setControlProperty(node, CONSTRAINTS_PROPERTY_KEY, constraints)
    }

    static Constraints getConstraints(Node node) {
        (Constraints) getControlProperty(node, CONSTRAINTS_PROPERTY_KEY)
    }

    private final ObservableList<Node> controls

    public FormPane() {
        super()
        controls = FXCollections.observableArrayList()
        controls.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                requestLayout()
            }
        })
        controls.addListener(new ListChangeListener<Node>() {
            @Override
            void onChanged(ListChangeListener.Change<? extends Node> change) {
                if (change.next()) {
                    if (change.wasAdded()) {
                        change.addedSubList.each { control ->
                            if (getConstraints(control) == null) {
                                setConstraints(control, new Constraints(0, 0, 0, 0))
                            }
                        }
                    }
                }
            }
        })
    }

//    Properties:

    private final ObjectProperty<Pos> alignment = new SimpleObjectProperty<>(this, 'alignment')

    ObjectProperty<Pos> alignmentProperty() {
        alignment
    }

    Pos getAlignment() {
        alignmentProperty().get()
    }

    void setAlignment(Pos alignment) {
        alignmentProperty().set(alignment)
    }

    private final ObjectProperty<ColumnConstraints> labelColumnConstraints =
            new SimpleObjectProperty<>(this, 'labelColumnConstraints')

    ObjectProperty<ColumnConstraints> labelColumnConstraintsProperty() {
        labelColumnConstraints
    }

    ColumnConstraints getLabelColumnConstraints() {
        def labelColumnConstraints = labelColumnConstraintsProperty().get()
        if (labelColumnConstraints == null) {
            labelColumnConstraints = new ColumnConstraints()
            setLabelColumnConstraints(labelColumnConstraints)
        }
        labelColumnConstraints
    }

    void setLabelColumnConstraints(ColumnConstraints labelColumnConstraints) {
        labelColumnConstraintsProperty().set(labelColumnConstraints)
    }

    private final ObjectProperty<ColumnConstraints> controlColumnConstraints =
            new SimpleObjectProperty<>(this, 'controlColumnConstraints')

    ObjectProperty<ColumnConstraints> controlColumnConstraintsProperty() {
        controlColumnConstraints
    }

    ColumnConstraints getControlColumnConstraints() {
        def controlColumnConstraints = controlColumnConstraintsProperty().get()
        if (controlColumnConstraints == null) {
            controlColumnConstraints = new ColumnConstraints()
            controlColumnConstraints.hgrow = Priority.SOMETIMES
            setControlColumnConstraints(controlColumnConstraints)
        }
        controlColumnConstraints
    }

    void setControlColumnConstraints(ColumnConstraints controlColumnConstraints) {
        controlColumnConstraintsProperty().set(controlColumnConstraints)
    }

    @Override
    public String getUserAgentStylesheet() {
        FormPane.getResource("skin/form-pane.css").toExternalForm()
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        new FormPaneSkin(this)
    }

    ObservableList<Node> getControls() {
        controls
    }

    void add(Labeled label, Node node, int columnIndex, int rowIndex, int colspan, int rowspan) {
        setLabel(node, label)
        setConstraints(node, new Constraints(columnIndex, rowIndex, colspan, rowspan))
        controls.add(node)
    }

    void add(Labeled label, Node node, int columnIndex, int rowIndex) {
        setLabel(node, label)
        setConstraints(node, new Constraints(columnIndex, rowIndex))
        controls.add(node)
    }
}
