/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.details.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.Grid;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPaneElement;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.ui.display.control.status.details.combatant.CombatantVisualization.getArmour;
import static com.evilbird.warcraft.item.ui.display.control.status.details.combatant.CombatantVisualization.getDamageMax;
import static com.evilbird.warcraft.item.ui.display.control.status.details.combatant.CombatantVisualization.getDamageMin;
import static com.evilbird.warcraft.item.ui.display.control.status.details.combatant.CombatantVisualization.getRange;
import static com.evilbird.warcraft.item.ui.display.control.status.details.combatant.CombatantVisualization.getSight;
import static com.evilbird.warcraft.item.ui.display.control.status.details.combatant.CombatantVisualization.getSpeed;

/**
 * Instances of this user interface show details about
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class CombatantDetailsPane extends Grid implements DetailsPaneElement
{
    private Label armour;
    private Label damage;
    private Label range;
    private Label sight;
    private Label speed;
    private DetailsPaneStyle style;

    @Inject
    public CombatantDetailsPane(Skin skin) {
        super(1, 5);

        setSkin(skin);
        setSize(160, 100);
        setCellSpacing(4);
        setCellWidth(160);
        setCellHeight(12);

        armour = createLabel(skin);
        damage = createLabel(skin);
        range = createLabel(skin);
        sight = createLabel(skin);
        speed = createLabel(skin);

        add(armour);
        add(damage);
        add(range);
        add(sight);
        add(speed);
    }

    public void setItem(Item item) {
        Combatant combatant = (Combatant)item;
        armour.setText(style.strings.getArmour(getArmour(combatant)));
        damage.setText(style.strings.getDamage(getDamageMin(combatant), getDamageMax(combatant)));
        range.setText(style.strings.getRange(getRange(combatant)));
        sight.setText(style.strings.getSight(getSight(combatant)));
        speed.setText(style.strings.getSpeed(getSpeed(combatant)));
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        this.style = skin.get(DetailsPaneStyle.class);
    }

    private Label createLabel(Skin skin) {
        Label result = new Label("", skin);
        result.setSize(160, 12);
        result.setAlignment(Align.center);
        return result;
    }
}
