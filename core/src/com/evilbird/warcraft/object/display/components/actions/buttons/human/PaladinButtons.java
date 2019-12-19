/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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

import static com.evilbird.warcraft.data.spell.Spell.Exorcism;
import static com.evilbird.warcraft.data.spell.Spell.Heal;
import static com.evilbird.warcraft.data.spell.Spell.HolyVision;
import static com.evilbird.warcraft.data.upgrade.Upgrade.ExorcismUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.HealingUpgrade;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DefendButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ExorcismButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.HealButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.HolyVisionButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PatrolButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.StopButton;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Paladin is selected.
 *
 * @author Blair Butterworth
 */
public class PaladinButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        asList(MoveButton, StopButton, AttackButton, PatrolButton, DefendButton, HolyVisionButton);

    private static final List<ActionButtonType> CASTING_BUTTONS =
        singletonList(CancelButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        SpellCaster paladin = (SpellCaster) gameObject;
        if (paladin.getCastingSpell() != null) {
            return CASTING_BUTTONS;
        }
        return getActionButtons(gameObject);
    }

    private List<ActionButtonType> getActionButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        List<ActionButtonType> buttons = new ArrayList<>(BASIC_BUTTONS);
        addUpgradeDependentButton(player, buttons, ExorcismButton, ExorcismUpgrade);
        addUpgradeDependentButton(player, buttons, HealButton, HealingUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        SpellCaster paladin = (SpellCaster) gameObject;
        switch (button) {
            case HolyVisionButton: return hasMana(paladin, HolyVision);
            case ExorcismButton: return hasMana(paladin, Exorcism);
            case HealButton: return hasMana(paladin, Heal);
            default: return true;
        }
    }
}
