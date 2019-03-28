/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.hud.HudControl;
import com.evilbird.warcraft.item.hud.control.status.details.DetailsPane;
import com.evilbird.warcraft.item.hud.control.status.selection.SelectionPane;

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
    private boolean invalidated;

    public StatusPane(Skin skin) {
        this.invalidated = true;
        this.selection = new ArrayList<>();
        this.detailsPane = new DetailsPane(skin);
        this.selectionPane = new SelectionPane(skin);

        addItem(selectionPane);
        setSize(176, 176);
        setIdentifier(HudControl.StatePane);
        setType(HudControl.StatePane);
        setTouchable(Touchable.childrenOnly);
    }

    public void updateSelection(Item item, boolean selected) {
        invalidated = true;
        if (selected) {
            selection.add(item);
        } else {
            selection.remove(item);
        }
    }

    public void updateSelection(Collection<Item> newSelection) {
        selection.clear();
        selection.addAll(newSelection);
        invalidated = true;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (invalidated) {
            invalidated = false;
            clearItems();
            updateDisplay();
        }
    }

    private void updateDisplay() {
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
}
