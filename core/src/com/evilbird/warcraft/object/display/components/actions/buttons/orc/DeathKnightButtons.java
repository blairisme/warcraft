/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.orc;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.BasicButtonController;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.data.spell.Spell.DeathAndDecay;
import static com.evilbird.warcraft.data.spell.Spell.DeathCoil;
import static com.evilbird.warcraft.data.spell.Spell.Haste;
import static com.evilbird.warcraft.data.spell.Spell.RaiseDead;
import static com.evilbird.warcraft.data.spell.Spell.TouchOfDarkness;
import static com.evilbird.warcraft.data.spell.Spell.UnholyArmour;
import static com.evilbird.warcraft.data.spell.Spell.Whirlwind;
import static com.evilbird.warcraft.data.upgrade.Upgrade.DeathAndDecayUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.HasteUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RaiseTheDeadUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.UnholyArmourUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.WhirlwindUpgrade;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DeathAndDecayButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DeathCoilButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.HasteButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RaiseDeadButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.StopButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TouchOfDarknessButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.UnholyArmourButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.WhirlwindButton;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when an Orcish Death Knight is selected.
 *
 * @author Blair Butterworth
 */
public class DeathKnightButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        asList(MoveButton, StopButton, TouchOfDarknessButton, DeathCoilButton);

    private static final List<ActionButtonType> CASTING_BUTTONS =
        singletonList(CancelButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        SpellCaster caster = (SpellCaster) gameObject;
        if (caster.getCastingSpell() != null) {
            return CASTING_BUTTONS;
        }
        return getActionButtons(gameObject);
    }

    private List<ActionButtonType> getActionButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        List<ActionButtonType> buttons = new ArrayList<>(BASIC_BUTTONS);
        addUpgradeDependentButton(player, buttons, HasteButton, HasteUpgrade);
        addUpgradeDependentButton(player, buttons, RaiseDeadButton, RaiseTheDeadUpgrade);
        addUpgradeDependentButton(player, buttons, WhirlwindButton, WhirlwindUpgrade);
        addUpgradeDependentButton(player, buttons, UnholyArmourButton, UnholyArmourUpgrade);
        addUpgradeDependentButton(player, buttons, DeathAndDecayButton, DeathAndDecayUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        SpellCaster caster = (SpellCaster) gameObject;
        switch (button) {
            case DeathAndDecayButton: return hasMana(caster, DeathAndDecay);
            case DeathCoilButton: return hasMana(caster, DeathCoil);
            case HasteButton: return hasMana(caster, Haste);
            case RaiseDeadButton: return hasMana(caster, RaiseDead);
            case TouchOfDarknessButton: return hasMana(caster, TouchOfDarkness);
            case UnholyArmourButton: return hasMana(caster, UnholyArmour);
            case WhirlwindButton: return hasMana(caster, Whirlwind);
            default: return true;
        }
    }
}
