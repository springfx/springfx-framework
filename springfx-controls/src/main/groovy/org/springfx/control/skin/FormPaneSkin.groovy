package org.springfx.control.skin

import javafx.collections.ListChangeListener
import javafx.collections.ListChangeListener.Change
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.control.SkinBase
import javafx.scene.layout.GridPane
import org.springfx.control.FormPane

/**
 * Skin for {@link FormPane}.
 *
 * @author Stephan Grundner
 * @since 1.0
 */
class FormPaneSkin extends SkinBase<FormPane> {

    private GridPane gridPane

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    public FormPaneSkin(FormPane control) {
        super(control)
        children.setAll(gridPane = new GridPane())
        gridPane.styleClass.add('grid')
        skinnable.styleClass.setAll('form-pane')

        def controls = skinnable.controls
        controls.addListener(new ListChangeListener<Node>() {
            @Override
            void onChanged(Change<? extends Node> change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        controlsAdded(change.addedSubList)
                    }
                    if (change.wasRemoved()) {
                        controlsRemoved(change.removed)
                    }
                }
            }
        })
        controlsAdded(controls)
    }

    private static int colspan(Integer value) {
        if (value > 1) {
            return (value * 2) - 1
        }
        value ?: 1
    }

    private void controlsAdded(List<? extends Node> added) {
        added.eachWithIndex { Node node, int i ->
            def label = skinnable.getLabel(node)
            def constraints = FormPane.getConstraints(node)
            assert constraints != null
            if (label) {
                if (label instanceof Label) {
                    if (label.labelFor == null) {
                        label.labelFor = node
                    }
                }
                gridPane.add(label, constraints.columnIndex * 2, constraints.rowIndex)
            }
            gridPane.add(node,
                    (constraints.columnIndex * 2) + 1,
                    constraints.rowIndex,
                    colspan(constraints.colspan),
                    constraints.rowspan ?: 1)
        }
    }

    private void controlsRemoved(List<? extends Node> removed) {
        removed.each { node ->
            gridPane.children.remove(node)
        }
    }
}
