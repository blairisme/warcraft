/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.warcraft.action.highlight.HighlightActions.HighlightPlayerCombatant;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.HealingButton;
import static com.evilbird.warcraft.item.unit.UnitType.Paladin;

/**
 * Defines user interactions that result in spell casting.
 *
 * @author Blair Butterworth
 */
public class SpellInteractions extends InteractionContainer
{
    @Inject
    public SpellInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        healSpell();
    }

    private void healSpell() {
        addAction(HighlightPlayerCombatant)
            .whenTarget(HealingButton)
            .whenSelected(Paladin)
            .appliedTo(Selected)
            .appliedAs(Addition);
    }
}
