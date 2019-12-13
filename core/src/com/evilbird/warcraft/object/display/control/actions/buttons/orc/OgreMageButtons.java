/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions.buttons.orc;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.control.actions.buttons.BasicButtonController;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.data.spell.Spell.Bloodlust;
import static com.evilbird.warcraft.data.spell.Spell.EyeOfKilrogg;
import static com.evilbird.warcraft.data.spell.Spell.Runes;
import static com.evilbird.warcraft.data.upgrade.Upgrade.BloodlustUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RunesUpgrade;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.BloodlustButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.EyeOfKilroggButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.RunesButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.StopButton;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when an Orcish Ogre Mage is selected.
 *
 * @author Blair Butterworth
 */
public class OgreMageButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        asList(MoveButton, StopButton, AttackButton, EyeOfKilroggButton);

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
        addUpgradeDependentButton(player, buttons, BloodlustButton, BloodlustUpgrade);
        addUpgradeDependentButton(player, buttons, RunesButton, RunesUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        SpellCaster caster = (SpellCaster) gameObject;
        switch (button) {
            case BloodlustButton: return hasMana(caster, Bloodlust);
            case EyeOfKilroggButton: return hasMana(caster, EyeOfKilrogg);
            case RunesButton: return hasMana(caster, Runes);
            default: return true;
        }
    }
}
