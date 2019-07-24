/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.ui.display.HudControl;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPane;
import com.evilbird.warcraft.item.ui.display.control.status.selection.SelectionPane;
import com.evilbird.warcraft.item.unit.building.Building;

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
public class StatusPane extends ItemGroup
{
    private DetailsPane detailsPane;
    private SelectionPane selectionPane;
    private Collection<Item> selection;

    public StatusPane(Skin skin) {
        this.selection = new ArrayList<>();
        this.detailsPane = new DetailsPane(skin);
        this.selectionPane = new SelectionPane(skin);

        setSize(176, 176);
        setType(HudControl.StatePane);
        setIdentifier(HudControl.StatePane);
        setTouchable(Touchable.enabled);
        addItem(selectionPane);
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

    public Collection<Item> getSelected() {
        return selection;
    }

    public void setSelected(Item item, boolean selected) {
        if (selected) {
            selection.add(item);
        } else {
            selection.remove(item);
        }
        updateDisplay();
    }

    public void setSelected(Collection<Item> selected) {
        selection.clear();
        selection.addAll(selected);
        updateDisplay();
    }

    private void updateDisplay() {
        clearItems();
        if (selection.size() == 1) {
            showDetails(selection);
        } else {
            showSelection(selection);
        }
    }

    private void showDetails(Collection<Item> selection) {
        detailsPane.setItem(selection.iterator().next());
        addItem(detailsPane);
    }

    private void showSelection(Collection<Item> selection) {
        selectionPane.setItems(selection);
        addItem(selectionPane);
    }

    private boolean isShown(Item item) {
        Collection<Item> items = getItems();
        return items.contains(item);
    }
}
