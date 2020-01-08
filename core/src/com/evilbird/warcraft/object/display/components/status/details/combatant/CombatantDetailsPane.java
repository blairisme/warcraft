/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.status.details.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.control.Grid;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneElement;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

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

    public void setItem(GameObject gameObject) {
        Combatant combatant = (Combatant) gameObject;
        setArmourText(combatant);
        setDamageText(combatant);
        setRangeText(combatant);
        setSightText(combatant);
        setSpeedText(combatant);
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        this.style = skin.get(DetailsPaneStyle.class);
    }

    protected Label createLabel(Skin skin) {
        Label result = new Label("", skin);
        result.setSize(160, 12);
        result.setAlignment(Align.center);
        return result;
    }

    private void setArmourText(Combatant combatant) {
        int basic = CombatantVisualization.getArmour(combatant);
        int upgraded = CombatantVisualization.getArmourUpgrade(combatant);
        armour.setText(style.strings.getArmour(basic, upgraded));
    }

    private void setDamageText(Combatant combatant) {
        int damageMin = CombatantVisualization.getDamageMin(combatant);
        int damageMax = CombatantVisualization.getDamageMax(combatant);
        int damageUpgrade = CombatantVisualization.getDamageUpgrade(combatant);
        damage.setText(style.strings.getDamage(damageMin, damageMax, damageUpgrade));
    }

    private void setRangeText(Combatant combatant) {
        int value = CombatantVisualization.getRange(combatant);
        range.setText(style.strings.getRange(value));
    }

    private void setSightText(Combatant combatant) {
        int value = CombatantVisualization.getSight(combatant);
        sight.setText(style.strings.getSight(value));
    }

    private void setSpeedText(Combatant combatant) {
        int value = CombatantVisualization.getSpeed(combatant);
        speed.setText(style.strings.getSpeed(value));
    }
}
