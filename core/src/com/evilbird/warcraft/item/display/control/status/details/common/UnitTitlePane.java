/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.status.details.common;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.Grid;
import com.evilbird.warcraft.item.display.control.common.UnitPane;
import com.evilbird.warcraft.item.display.control.status.details.DetailsPaneElement;
import com.evilbird.warcraft.item.display.control.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * Represents a user interface control that displays the icon and name of a
 * {@link Unit}.
 *
 * @author Blair Butterworth
 */
public class UnitTitlePane extends Grid implements DetailsPaneElement
{
    private Label title;
    private UnitPane icon;
    private DetailsPaneStyle style;

    public UnitTitlePane(Skin skin) {
        super(2, 1);
        setSkin(skin);
        this.icon = addIcon(skin);
        this.title = addTitle(skin);
    }

    public void setItem(Item item) {
        Unit unit = (Unit)item;
        icon.setItem(unit);
        title.setText(style.strings.getName((UnitType)item.getType()));
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        this.style = skin.get(DetailsPaneStyle.class);
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
        name.setWrap(true);

        Cell nameCell = add(name);
        nameCell.width(100);
        nameCell.height(15);

        return name;
    }
}
