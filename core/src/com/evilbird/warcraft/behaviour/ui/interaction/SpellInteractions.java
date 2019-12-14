/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.spell.SpellActions;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.object.selector.SelectorType;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.function.Predicate;

import static com.evilbird.warcraft.action.selector.SelectorActions.HideSelector;
import static com.evilbird.warcraft.action.spell.SpellActions.BlizzardSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.BloodlustSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.BloodlustSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.DeathAndDecaySpell;
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
import static com.evilbird.warcraft.action.spell.SpellActions.RunesSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.SlowSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.SlowSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.SpellDeselect;
import static com.evilbird.warcraft.action.spell.SpellActions.UnholyArmourSelect;
import static com.evilbird.warcraft.action.spell.SpellActions.UnholyArmourSpell;
import static com.evilbird.warcraft.action.spell.SpellActions.WhirlwindSpell;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Replacement;
import static com.evilbird.warcraft.data.spell.Spell.Bloodlust;
import static com.evilbird.warcraft.data.spell.Spell.DeathCoil;
import static com.evilbird.warcraft.data.spell.Spell.Exorcism;
import static com.evilbird.warcraft.data.spell.Spell.FlameShield;
import static com.evilbird.warcraft.data.spell.Spell.Haste;
import static com.evilbird.warcraft.data.spell.Spell.Heal;
import static com.evilbird.warcraft.data.spell.Spell.Invisibility;
import static com.evilbird.warcraft.data.spell.Spell.Polymorph;
import static com.evilbird.warcraft.data.spell.Spell.Slow;
import static com.evilbird.warcraft.data.spell.Spell.UnholyArmour;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.BloodlustButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.DeathCoilButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.ExorcismButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.EyeOfKilroggButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.FlameShieldButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.HasteButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.HealButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.InvisibilityButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.PolymorphButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.RaiseDeadButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.SlowButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.UnholyArmourButton;
import static com.evilbird.warcraft.object.selector.SelectorType.BlizzardSelector;
import static com.evilbird.warcraft.object.selector.SelectorType.DeathAndDecaySelector;
import static com.evilbird.warcraft.object.selector.SelectorType.RuneTrapSelector;
import static com.evilbird.warcraft.object.selector.SelectorType.WhirlwindSelector;
import static com.evilbird.warcraft.object.unit.UnitType.DeathKnight;
import static com.evilbird.warcraft.object.unit.UnitType.Mage;
import static com.evilbird.warcraft.object.unit.UnitType.OgreMage;
import static com.evilbird.warcraft.object.unit.UnitType.Paladin;

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
        selectorSpell(Mage, BlizzardSelector, BlizzardSpell);

        highlightSpell(Mage, FlameShield, FlameShieldButton, FlameShieldSelect, FlameShieldSpell);
        highlightSpell(Mage, Polymorph, PolymorphButton, PolymorphSelect, PolymorphSpell);
        highlightSpell(Mage, Slow, SlowButton, SlowSelect, SlowSpell);
        highlightSpell(Mage, Invisibility, InvisibilityButton, InvisibilitySelect, InvisibilitySpell);
    }

    private void paladinSpells() {
        highlightSpell(Paladin, Exorcism, ExorcismButton, ExorcismSelect, ExorcismSpell);
        highlightSpell(Paladin, Heal, HealButton, HealSelect, HealSpell);
        //buffSpell(Paladin, HolyVision, HolyVisionButton, HolyVisionSelect, HolyVisionSpell);
    }

    private void deathKnightSpells() {
        highlightSpell(DeathKnight, DeathCoil, DeathCoilButton, DeathCoilSelect, DeathCoilSpell);
        highlightSpell(DeathKnight, Haste, HasteButton, HasteSelect, HasteSpell);
        highlightSpell(DeathKnight, UnholyArmour, UnholyArmourButton, UnholyArmourSelect, UnholyArmourSpell);

        selectorSpell(DeathKnight, DeathAndDecaySelector, DeathAndDecaySpell);
        selectorSpell(DeathKnight, WhirlwindSelector, WhirlwindSpell);

        instantSpell(DeathKnight, RaiseDeadButton, RaiseDeadSpell);
    }

    private void ogreMageSpells() {
        instantSpell(OgreMage, EyeOfKilroggButton, EyeOfKilroggSpell);
        highlightSpell(OgreMage, Bloodlust, BloodlustButton, BloodlustSelect, BloodlustSpell);

        selectorSpell(OgreMage, RuneTrapSelector, RunesSpell);
    }

    private void instantSpell(
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

    private void highlightSpell(
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

    private void selectorSpell(
        UnitType caster,
        SelectorType selector,
        SpellActions action)
    {
        addAction(HideSelector, action)
            .whenTarget(selector)
            .whenSelected(caster)
            .appliedTo(Selected)
            .appliedAs(Addition);
    }

    private static Predicate<GameObject> isCastingSpell(Spell spell) {
        return item -> UnitOperations.isCastingSpell(item, spell);
    }
}
