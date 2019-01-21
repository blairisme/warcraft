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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.warcraft.item.hud.HudControls;
import com.evilbird.warcraft.item.hud.common.UnitPane;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Provider;

/**
 * Instances of this class display the selected units.
 *
 * @author Blair Butterworth
 */
//TODO: Scale flexibly
public class SelectionPane extends GridPane
{
    private Provider<UnitPane> tileProvider;

    public SelectionPane(Provider<UnitPane> tileProvider) {
        super(3, 3);
        this.tileProvider = tileProvider;
        setSize(176, 176);
        setCellPadding(3);
        setCellWidthMinimum(54);
        setCellHeightMinimum(53);
        setId(HudControls.SelectionPane);
        setType(HudControls.SelectionPane);
        setTouchable(Touchable.childrenOnly);
    }

    public void setItems(Collection<Item> selection) {
        clearCells();
        setCells(getUnitPanes(selection));
    }

    private Collection<Item> getUnitPanes(Collection<Item> items) {
        Collection<Item> result = new ArrayList<Item>(items.size());
        for (Item item : items) {
            if (item instanceof Unit) {
                result.add(getUnitPane((Unit) item));
            }
        }
        return result;
    }

    private Item getUnitPane(Unit unit) {
        UnitPane result = tileProvider.get();
        result.setItem(unit);
        result.setSize(54, 53);
        return result;
    }
}
