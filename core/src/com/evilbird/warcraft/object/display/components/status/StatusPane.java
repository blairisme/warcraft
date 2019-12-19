/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.components.status;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.display.components.UserInterfaceComponent;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPane;
import com.evilbird.warcraft.object.display.components.status.selection.SelectionPane;
import com.evilbird.warcraft.object.unit.building.Building;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a user interface panel displayed the top of the heads up display
 * control bar. Displays the state of the currently selected unit if only one
 * unit is selected. If more than one unit is selected then a list of tiles
 * representing each of the selected units are displayed.
 *
 * @author Blair Butterworth
 */
public class StatusPane extends GameObjectGroup
{
    private DetailsPane detailsPane;
    private SelectionPane selectionPane;
    private Collection<GameObject> selection;

    public StatusPane(Skin skin) {
        this.selection = new ArrayList<>();
        this.detailsPane = new DetailsPane(skin);
        this.selectionPane = new SelectionPane(skin);

        setSize(176, 176);
        setType(UserInterfaceComponent.StatePane);
        setIdentifier(UserInterfaceComponent.StatePane);
        setTouchable(Touchable.enabled);
        addObject(selectionPane);
    }

    public void setConstructing(Building building, boolean constructing) {
        if (selection.contains(building) && isShown(detailsPane)) {
            detailsPane.setConstructing(building, constructing);
        }
    }

    public void setProducing(Building building, boolean producing) {
        if (selection.contains(building) && isShown(detailsPane)) {
            detailsPane.setProducing(building, producing);
        }
    }

    public void setPlayerResource(ResourceType resource, float value) {
        if (isShown(detailsPane)) {
            detailsPane.setResource(resource, value);
        }
    }

    public void setItemResource(GameObject recipient, ResourceType resource, float value) {
        if (isShown(detailsPane)) {
            detailsPane.setResource(resource, value);
        }
    }

    public Collection<GameObject> getSelected() {
        return selection;
    }

    public void setSelected(GameObject gameObject, boolean selected) {
        if (selected) {
            selection.add(gameObject);
        } else {
            selection.remove(gameObject);
        }
        updateDisplay();
    }

    public void setSelected(Collection<GameObject> selected) {
        selection.clear();
        selection.addAll(selected);
        updateDisplay();
    }

    private void updateDisplay() {
        removeObjects();
        if (selection.size() == 1) {
            showDetails(selection);
        } else {
            showSelection(selection);
        }
    }

    private void showDetails(Collection<GameObject> selection) {
        detailsPane.setItem(selection.iterator().next());
        addObject(detailsPane);
    }

    private void showSelection(Collection<GameObject> selection) {
        selectionPane.setItems(selection);
        addObject(selectionPane);
    }

    private boolean isShown(GameObject gameObject) {
        Collection<GameObject> gameObjects = getObjects();
        return gameObjects.contains(gameObject);
    }
}
