/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.combatant;

import com.evilbird.engine.common.control.BorderPane;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this user interface show details about
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class CombatantDetailsPane extends BorderPane
{
    @Inject
    public CombatantDetailsPane()
    {
    }

    public void setCombatant(Combatant combatant)
    {
        showMeleeDetailsPane(combatant);
    }

    private void showMeleeDetailsPane(Combatant combatant)
    {
        MeleeDetailsPane detailsPane = new MeleeDetailsPane();
        detailsPane.setCombatant(combatant);
        setCenter(detailsPane);
    }
}
