/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.details.common;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.hud.common.UnitPane;
import com.evilbird.warcraft.item.hud.control.status.details.DetailsPaneElement;
import com.evilbird.warcraft.item.unit.Unit;

/**
 * Represents a user interface control that displays the icon and name of a
 * {@link Unit}.
 *
 * @author Blair Butterworth
 */
public class UnitTitlePane extends GridItem implements DetailsPaneElement
{
    private Label title;
    private UnitPane icon;

    public UnitTitlePane(Skin skin) {
        super(2, 1);
        this.icon = addIcon(skin);
        this.title = addTitle(skin);
    }

    public void setItem(Item item) {
        Unit unit = (Unit)item;
        icon.setItem(unit);
        title.setText(unit.getName());
    }

    private UnitPane addIcon(Skin skin) {
        UnitPane unitPane = new UnitPane(skin);
        unitPane.setSize(54, 54);

        Cell unitCell = add(unitPane);
        unitCell.width(54);
        unitCell.height(53);

        return unitPane;
    }

    private Label addTitle(Skin skin) {
        Label name = new Label("", skin);
        name.setAlignment(Align.center);

        Cell nameCell = add(name);
        nameCell.width(100);
        nameCell.height(15);

        return name;
    }
}
