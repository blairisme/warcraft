/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.combatant;

import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.TextLabel;
import com.evilbird.engine.item.control.TextLabelAlignment;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

public class MeleeDetailsPane extends GridPane
{
    private Combatant combatant;
    private TextLabel armour;
    private TextLabel damage;
    private TextLabel range;
    private TextLabel sight;
    private TextLabel speed;

    @Inject
    public MeleeDetailsPane() {
        super(1, 5);
        armour = createLabel("Armour");
        damage = createLabel("Damage");
        range = createLabel("Range");
        sight = createLabel("Sight");
        speed = createLabel("Speed");

        setSize(160, 100);
        setCellSpacing(4);
        setCell(armour, 0, 0);
        setCell(damage, 0, 1);
        setCell(range, 0, 2);
        setCell(sight, 0, 3);
        setCell(speed, 0, 4);
        setCellWidthMinimum(160);
        setCellHeightMinimum(12);
    }

    public void setCombatant(Combatant combatant) {
        this.combatant = combatant;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        armour.setText(getText("Armour", combatant.getDefence()));
        damage.setText(getText("Damage", combatant.getDamageMinimum(), combatant.getDamageMaximum()));
        range.setText(getText("Range", combatant.getRange()));
        sight.setText(getText("Sight", combatant.getSight()));
        speed.setText(getText("Speed", combatant.getSpeed()));
    }

    private String getText(String prefix, float suffix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append(": ");
        stringBuilder.append(Math.round(suffix));
        return stringBuilder.toString();
    }

    private String getText(String prefix, float suffixMin, float suffixMax) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append(": ");
        stringBuilder.append(Math.round(suffixMin));
        stringBuilder.append("-");
        stringBuilder.append(Math.round(suffixMax));
        return stringBuilder.toString();
    }

    private TextLabel createLabel(String text) {
        TextLabel result = new TextLabel();
        result.setText(text);
        result.setSize(160, 12);
        result.setAlignment(TextLabelAlignment.Center);
        return result;
    }
}
