/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.warcraft.action.spell.SpellActions;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.unit.UnitType;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.warcraft.action.spell.SpellActions.BloodlustSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.BloodlustSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.DeathCoilSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.DeathCoilSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.ExorcismSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.ExorcismSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.EyeOfKilroggSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.FlameShieldSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.FlameShieldSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.HasteSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.HasteSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.HealSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.HealSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.InvisibilitySelect;
import static com.evilbird.warcraft.action.spell.SpellActions.InvisibilitySpell;
import static com.evilbird.warcraft.action.spell.SpellActions.PolymorphSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.PolymorphSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.RaiseDeadSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.SlowSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.SlowSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.SpellDeselect;
import static com.evilbird.warcraft.action.spell.SpellActions.UnholyArmourSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.UnholyArmourSpell;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Replacement;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCastingSpell;
import static com.evilbird.warcraft.item.common.spell.Spell.Bloodlust;
import static com.evilbird.warcraft.item.common.spell.Spell.DeathCoil;
import static com.evilbird.warcraft.item.common.spell.Spell.Exorcism;
import static com.evilbird.warcraft.item.common.spell.Spell.FlameShield;
import static com.evilbird.warcraft.item.common.spell.Spell.Haste;
import static com.evilbird.warcraft.item.common.spell.Spell.Heal;
import static com.evilbird.warcraft.item.common.spell.Spell.Invisibility;
import static com.evilbird.warcraft.item.common.spell.Spell.Polymorph;
import static com.evilbird.warcraft.item.common.spell.Spell.Slow;
import static com.evilbird.warcraft.item.common.spell.Spell.UnholyArmour;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BloodlustButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.DeathCoilButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ExorcismButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.EyeOfKilroggButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.FlameShieldButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.HasteButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.HealButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.InvisibilityButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.PolymorphButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.RaiseDeadButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.SlowButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.UnholyArmourButton;
import static com.evilbird.warcraft.item.unit.UnitType.DeathKnight;
import static com.evilbird.warcraft.item.unit.UnitType.Mage;
import static com.evilbird.warcraft.item.unit.UnitType.OgreMage;
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
        mageSpells();
        paladinSpells();
        deathKnightSpells();
        ogreMageSpells();
    }

    private void mageSpells() {
        //buffSpell(Mage, Blizzard, BlizzardButton, BlizzardSelect, BlizzardSpell);
        targetedSpell(Mage, FlameShield, FlameShieldButton, FlameShieldSelect, FlameShieldSpell);
        targetedSpell(Mage, Polymorph, PolymorphButton, PolymorphSelect, PolymorphSpell);
        targetedSpell(Mage, Slow, SlowButton, SlowSelect, SlowSpell);
        targetedSpell(Mage, Invisibility, InvisibilityButton, InvisibilitySelect, InvisibilitySpell);
    }

    private void paladinSpells() {
        targetedSpell(Paladin, Exorcism, ExorcismButton, ExorcismSelect, ExorcismSpell);
        targetedSpell(Paladin, Heal, HealButton, HealSelect, HealSpell);
        //buffSpell(Paladin, HolyVision, HolyVisionButton, HolyVisionSelect, HolyVisionSpell);
    }

    private void deathKnightSpells() {
        targetedSpell(DeathKnight, DeathCoil, DeathCoilButton, DeathCoilSelect, DeathCoilSpell);
        targetedSpell(DeathKnight, Haste, HasteButton, HasteSelect, HasteSpell);
        targetedSpell(DeathKnight, UnholyArmour, UnholyArmourButton, UnholyArmourSelect, UnholyArmourSpell);
        //targetedSpell(DeathKnight, DeathAndDecay, DeathAndDecayButton, DeathAndDecaySelect, DeathAndDecaySpell);
        //targetedSpell(DeathKnight, Whirlwind, WhirlwindButton, WhirlwindSelect, WhirlwindSpell);
        nonTargetedSpell(DeathKnight, RaiseDeadButton, RaiseDeadSpell);
    }

    private void ogreMageSpells() {
        nonTargetedSpell(OgreMage, EyeOfKilroggButton, EyeOfKilroggSpell);
        targetedSpell(OgreMage, Bloodlust, BloodlustButton, BloodlustSelect, BloodlustSpell);
        //targetedSpell(OgreMage, Runes, RunesButton, RunesSelect, RunesSpell);
    }

    private void nonTargetedSpell(
        UnitType caster,
        ActionButtonType button,
        SpellActions action)
    {
        addAction(action)
            .whenTarget(button)
            .whenSelected(caster)
            .appliedTo(Selected)
            .appliedAs(Addition);
    }

    private void targetedSpell(
        UnitType caster,
        Spell spell,
        ActionButtonType button,
        SpellActions select,
        SpellActions action)
    {
        addAction(select)
            .whenTarget(button)
            .whenSelected(caster)
            .appliedTo(Selected)
            .appliedAs(Addition);

        addAction(action, SpellDeselect)
            .whenTarget(UnitOperations::isHighlighted)
            .whenSelected(isCastingSpell(spell))
            .appliedTo(Selected)
            .appliedAs(Replacement);

        addAction(SpellDeselect)
            .whenSelected(isCastingSpell(spell))
            .whenTarget(CancelButton)
            .appliedTo(Selected)
            .appliedAs(Addition);
    }
}
