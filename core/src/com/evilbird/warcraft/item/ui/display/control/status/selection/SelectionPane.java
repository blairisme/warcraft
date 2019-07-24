/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.selection;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.ui.display.HudControl;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class display the selected units.
 *
 * @author Blair Butterworth
 */
public class SelectionPane extends GridItem
{
    private static final int SELECTION_MAX = 9;

    public SelectionPane(Skin skin) {
        super(3, 3);
        setSkin(skin);
        setBackground("selection-panel");
        setSize(176, 176);
        setCellPadding(3);
        setCellWidth(54);
        setCellHeight(53);
        setIdentifier(HudControl.SelectionPane);
        setType(HudControl.SelectionPane);
        setTouchable(Touchable.childrenOnly);
    }

    public void setItems(Collection<Item> selection) {
        clearItems();
        addItems(getUnitPanes(selection));
    }

    private Collection<Item> getUnitPanes(Collection<Item> items) {
        Collection<Item> result = new ArrayList<>(items.size());
        for (Item item : items) {
            if (item instanceof Unit) {
                result.add(getSelectionButton(item));
            }
            if (result.size() == SELECTION_MAX) {
                break;
            }
        }
        return result;
    }

    private SelectionButton getSelectionButton(Item item) {
        SelectionButton result = new SelectionButton(getSkin());
        result.set(item);
        return result;
    }
}
