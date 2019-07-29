/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.details.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.ui.display.control.common.UnitPane;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPaneElement;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import static com.evilbird.warcraft.item.ui.display.control.status.details.combatant.CombatantVisualization.getLevel;

/**
 * Represents a user interface control that displays the icon, name and level
 * of a {@link Combatant}.
 *
 * @author Blair Butterworth
 */
public class CombatantTitlePane extends GridItem implements DetailsPaneElement
{
    private Label title;
    private Label level;
    private GridItem labels;
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

    public void setItem(Item item) {
        Combatant combatant = (Combatant)item;
        icon.setItem(combatant);
        title.setText(style.strings.getName(combatant));
        level.setText(style.strings.getLevel(getLevel(combatant)));
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

    private GridItem addContainer(Skin skin) {
        GridItem container = new GridItem(1, 2);
        container.setSkin(skin);

        Cell nameCell = add(container);
        nameCell.width(100);
        nameCell.height(50);

        return container;
    }

    private Label addLabel(GridItem parent, Skin skin) {
        Label name = new Label("", skin);
        name.setAlignment(Align.center);

        Cell nameCell = parent.add(name);
        nameCell.width(100);
        nameCell.height(15);

        return name;
    }
}
