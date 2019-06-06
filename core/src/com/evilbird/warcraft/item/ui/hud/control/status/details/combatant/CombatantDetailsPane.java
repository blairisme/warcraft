/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.status.details.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.ui.hud.control.status.details.DetailsPaneElement;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.unit.combatant.CombatantVisualization.getDamageMaximum;
import static com.evilbird.warcraft.item.unit.combatant.CombatantVisualization.getDamageMinimum;
import static com.evilbird.warcraft.item.unit.combatant.CombatantVisualization.getDefence;
import static com.evilbird.warcraft.item.unit.combatant.CombatantVisualization.getMovementSpeed;
import static com.evilbird.warcraft.item.unit.combatant.CombatantVisualization.getRange;
import static com.evilbird.warcraft.item.unit.combatant.CombatantVisualization.getSight;

/**
 * Instances of this user interface show details about
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class CombatantDetailsPane extends GridItem implements DetailsPaneElement
{
    private Label armour;
    private Label damage;
    private Label range;
    private Label sight;
    private Label speed;

    @Inject
    public CombatantDetailsPane(Skin skin) {
        super(1, 5);

        armour = createLabel("Armour", skin);
        damage = createLabel("Damage", skin);
        range = createLabel("Range", skin);
        sight = createLabel("Sight", skin);
        speed = createLabel("Speed", skin);

        setSize(160, 100);
        setCellSpacing(4);
        setCellWidth(160);
        setCellHeight(12);

        add(armour);
        add(damage);
        add(range);
        add(sight);
        add(speed);
    }

    public void setItem(Item item) {
        Combatant combatant = (Combatant)item;
        armour.setText(getText("Armour", getDefence(combatant)));
        damage.setText(getText("Damage", getDamageMinimum(combatant), getDamageMaximum(combatant)));
        range.setText(getText("Range", getRange(combatant)));
        sight.setText(getText("Sight", getSight(combatant)));
        speed.setText(getText("Speed", getMovementSpeed(combatant)));
    }

    private String getText(String prefix, int suffix) {
        return getText(prefix, String.valueOf(suffix));
    }

    private String getText(String prefix, String suffix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append(": ");
        stringBuilder.append(suffix);
        return stringBuilder.toString();
    }

    private String getText(String prefix, String suffixMin, String suffixMax) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append(": ");
        stringBuilder.append(suffixMin);
        stringBuilder.append("-");
        stringBuilder.append(suffixMax);
        return stringBuilder.toString();
    }

    private Label createLabel(String text, Skin skin) {
        Label result = new Label(text, skin);
        result.setSize(160, 12);
        result.setAlignment(Align.center);
        return result;
    }
}
