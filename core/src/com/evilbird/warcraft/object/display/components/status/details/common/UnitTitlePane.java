/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.status.details.common;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.control.Grid;
import com.evilbird.warcraft.object.display.components.common.UnitPane;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneElement;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;

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

    public void setItem(GameObject gameObject) {
        Unit unit = (Unit) gameObject;
        icon.setItem(unit);
        title.setText(style.strings.getName((UnitType) gameObject.getType()));
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
