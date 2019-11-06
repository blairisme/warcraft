/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.BasicButtonController;
import com.evilbird.warcraft.item.unit.combatant.SpellCaster;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.item.common.spell.Spell.Exorcism;
import static com.evilbird.warcraft.item.common.spell.Spell.Heal;
import static com.evilbird.warcraft.item.common.spell.Spell.HolyVision;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.ExorcismUpgrade;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.HealingUpgrade;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.DefendButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ExorcismButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.HealButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.HolyVisionButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.PatrolButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.StopButton;
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
    public List<ActionButtonType> getButtons(Item item) {
        SpellCaster paladin = (SpellCaster)item;
        if (paladin.getCastingSpell() != null) {
            return CASTING_BUTTONS;
        }
        return getActionButtons(item);
    }

    private List<ActionButtonType> getActionButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        List<ActionButtonType> buttons = new ArrayList<>(BASIC_BUTTONS);
        addUpgradeButton(player, buttons, ExorcismButton, ExorcismUpgrade);
        addUpgradeButton(player, buttons, HealButton, HealingUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        SpellCaster paladin = (SpellCaster)item;
        switch (button) {
            case HolyVisionButton: return hasMana(paladin, HolyVision);
            case ExorcismButton: return hasMana(paladin, Exorcism);
            case HealButton: return hasMana(paladin, Heal);
            default: return true;
        }
    }
}
