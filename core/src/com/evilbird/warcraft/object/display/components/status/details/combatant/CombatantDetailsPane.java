/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
