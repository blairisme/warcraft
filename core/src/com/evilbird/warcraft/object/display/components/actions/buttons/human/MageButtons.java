/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.human;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.BasicButtonController;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.data.spell.Spell.Blizzard;
import static com.evilbird.warcraft.data.spell.Spell.Fireball;
import static com.evilbird.warcraft.data.spell.Spell.FlameShield;
import static com.evilbird.warcraft.data.spell.Spell.Invisibility;
import static com.evilbird.warcraft.data.spell.Spell.Lightning;
import static com.evilbird.warcraft.data.spell.Spell.Polymorph;
import static com.evilbird.warcraft.data.spell.Spell.Slow;
import static com.evilbird.warcraft.data.upgrade.Upgrade.BlizzardUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.FlameShieldUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.InvisibilityUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.PolymorphUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.SlowUpgrade;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BlizzardButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FireballButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FlameShieldButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.InvisibilityButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.LightningButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PolymorphButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.SlowButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.StopButton;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Mage is selected.
 *
 * @author Blair Butterworth
 */
public class MageButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        asList(MoveButton, StopButton, LightningButton,
               FireballButton);

    private static final List<ActionButtonType> CASTING_BUTTONS =
        singletonList(CancelButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        SpellCaster mage = (SpellCaster) gameObject;
        if (mage.getCastingSpell() != null) {
            return CASTING_BUTTONS;
        }
        return getActionButtons(gameObject);
    }

    private List<ActionButtonType> getActionButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        List<ActionButtonType> buttons = new ArrayList<>(BASIC_BUTTONS);
        addUpgradeDependentButton(player, buttons, SlowButton, SlowUpgrade);
        addUpgradeDependentButton(player, buttons, FlameShieldButton, FlameShieldUpgrade);
        addUpgradeDependentButton(player, buttons, InvisibilityButton, InvisibilityUpgrade);
        addUpgradeDependentButton(player, buttons, PolymorphButton, PolymorphUpgrade);
        addUpgradeDependentButton(player, buttons, BlizzardButton, BlizzardUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        SpellCaster mage = (SpellCaster) gameObject;
        switch (button) {
            case BlizzardButton: return hasMana(mage, Blizzard);
            case FireballButton: return hasMana(mage, Fireball);
            case FlameShieldButton: return hasMana(mage, FlameShield);
            case InvisibilityButton: return hasMana(mage, Invisibility);
            case LightningButton: return hasMana(mage, Lightning);
            case PolymorphButton: return hasMana(mage, Polymorph);
            case SlowButton: return hasMana(mage, Slow);
            default: return true;
        }
    }
}
