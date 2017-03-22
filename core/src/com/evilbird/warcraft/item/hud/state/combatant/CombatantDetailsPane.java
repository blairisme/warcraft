package com.evilbird.warcraft.item.hud.state.combatant;

import com.evilbird.engine.item.control.BorderPane;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
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
