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
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.hud.HudControls;
import com.evilbird.warcraft.item.placeholder.Placeholder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this user interface control displays the state of a given unit.
 *
 * @author Blair Butterworth
 */
//TODO: scale flexibly
public class StatePane extends ItemGroup
{
    private DetailsPane detailsPane;
    private SelectionPane selectionPane;
    private Collection<Item> selection;
    private boolean invalidated;

    @Inject
    public StatePane(
        DetailsPaneProvider detailsPaneProvider,
        SelectionPaneProvider selectionPaneProvider)
    {
        this.detailsPane = detailsPaneProvider.get();
        this.selectionPane = selectionPaneProvider.get();
        this.selection = new ArrayList<>();
        this.invalidated = true;

        addItem(selectionPane);
        setSize(176, 176);
        setId(HudControls.StatePane);
        setType(HudControls.StatePane);
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
            Collection<Item> items = removeHudElements(selection);

            if (items.size() == 1) {
                detailsPane.setItem(items.iterator().next());
                addItem(detailsPane);
            } else {
                selectionPane.setItems(items);
                addItem(selectionPane);
            }
        }
    }

    private Collection<Item> removeHudElements(Collection<Item> elements) {
        Collection<Item> result = new ArrayList<>(elements.size());
        for (Item element : elements) {
            if (!(element instanceof Placeholder)) {
                result.add(element);
            }
        }
        return result;
    }
}
