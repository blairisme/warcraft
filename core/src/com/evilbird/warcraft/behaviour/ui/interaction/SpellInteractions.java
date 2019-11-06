/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.warcraft.item.common.query.UnitOperations;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.warcraft.action.spell.SpellActions.HealSelection;
import static com.evilbird.warcraft.action.spell.SpellActions.HealSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.SpellDeselect;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Replacement;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCastingSpell;
import static com.evilbird.warcraft.item.common.spell.Spell.Heal;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.HealButton;
import static com.evilbird.warcraft.item.unit.UnitType.Paladin;

/**
 * Defines user interactions that result in spells.
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
        addAction(HealSelection)
            .whenTarget(HealButton)
            .whenSelected(Paladin)
            .appliedTo(Selected)
            .appliedAs(Addition);

        addAction(HealSpell, SpellDeselect)
            .whenTarget(UnitOperations::isHighlighted)
            .whenSelected(isCastingSpell(Heal))
            .appliedTo(Selected)
            .appliedAs(Replacement);

        addAction(SpellDeselect)
            .whenSelected(isCastingSpell(Heal))
            .whenTarget(CancelButton)
            .appliedTo(Selected)
            .appliedAs(Addition);
    }
}
