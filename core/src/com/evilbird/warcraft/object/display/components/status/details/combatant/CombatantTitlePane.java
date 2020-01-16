/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.status.details.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.control.Grid;
import com.evilbird.warcraft.object.display.components.common.UnitPane;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneElement;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

/**
 * Represents a user interface control that displays the icon, name and level
 * of a {@link Combatant}.
 *
 * @author Blair Butterworth
 */
public class CombatantTitlePane extends Grid implements DetailsPaneElement
{
    private Label title;
    private Label level;
    private Grid labels;
    private UnitPane icon;
    private DetailsPaneStyle style;

    public CombatantTitlePane(Skin skin) {
        super(2, 1);
        setSkin(skin);

        this.icon = addIcon(skin);
        this.labels = addContainer(skin);
        this.title = addLabel(labels, skin);
        this.level = addLabel(labels, skin);
    }

    public void setItem(GameObject gameObject) {
        Combatant combatant = (Combatant) gameObject;
        icon.setItem(combatant);
        title.setText(style.strings.getName(combatant));
        level.setText(style.strings.getLevel(CombatantVisualization.getLevel(combatant)));
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

    private Grid addContainer(Skin skin) {
        Grid container = new Grid(1, 2);
        container.setSkin(skin);

        Cell nameCell = add(container);
        nameCell.width(100);
        nameCell.expandY();

        return container;
    }

    private Label addLabel(Grid parent, Skin skin) {
        Label name = new Label("", skin);
        name.setAlignment(Align.center);
        name.setWrap(true);

        Cell nameCell = parent.add(name);
        nameCell.width(100);
        nameCell.expandY();

        return name;
    }
}
