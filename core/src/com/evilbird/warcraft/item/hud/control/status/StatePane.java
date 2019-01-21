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
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.hud.HudControls;
import com.evilbird.warcraft.item.placeholder.Placeholder;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

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

    @Inject
    public StatePane(
        DetailsPaneProvider detailsPaneProvider,
        SelectionPaneProvider selectionPaneProvider)
    {
        this.detailsPane = detailsPaneProvider.get();
        this.selectionPane = selectionPaneProvider.get();
        addItem(selectionPane);
        setSize(176, 176); //TODO: scale flexibly
        setId(HudControls.StatePane);
        setType(HudControls.StatePane);
        setTouchable(Touchable.childrenOnly);
    }

    public void setSelection(Collection<Item> newSelection) {
        if (!Objects.equals(selection, newSelection)) {
            selection = newSelection;
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
