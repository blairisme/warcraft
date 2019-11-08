/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.common;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDefence1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDefence2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SiegeDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SiegeDamage2;

/**
 * Controls the buttons shown when a Human Blacksmith is selected.
 *
 * @author Blair Butterworth
 */
public class BlacksmithButtons extends BasicButtonController
{
    @Override
    public List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        return player.getLevel() <= 5 ? getBasicUpgrades(player) : getAdvancedUpgrades(player);
    }

    private List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> getBasicUpgrades(Player player) {
        List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> buttons = new ArrayList<>();
        addUpgradeButton(player, buttons, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.MeleeDamage1Button, MeleeDamage1);
        addUpgradeButton(player, buttons, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.MeleeDefence1Button, MeleeDefence1);
        addUpgradeButton(player, buttons, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.SiegeDamage1Button, SiegeDamage1);
        return buttons;
    }

    private List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> getAdvancedUpgrades(Player player) {
        List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> buttons = getBasicUpgrades(player);
        addUpgradeButton(player, buttons, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.MeleeDamage2Button, MeleeDamage1, MeleeDamage2);
        addUpgradeButton(player, buttons, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.MeleeDefence2Button, MeleeDefence1, MeleeDefence2);
        addUpgradeButton(player, buttons, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.SiegeDamage2Button, SiegeDamage1, SiegeDamage2);
        return buttons;
    }

    @Override
    public boolean getEnabled(com.evilbird.warcraft.item.display.control.actions.ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case MeleeDamage1Button: return hasResources(player, MeleeDamage1);
            case MeleeDamage2Button: return hasResources(player, MeleeDamage2);
            case MeleeDefence1Button: return hasResources(player, MeleeDefence1);
            case MeleeDefence2Button: return hasResources(player, MeleeDefence2);
            case SiegeDamage1Button: return hasResources(player, SiegeDamage1);
            case SiegeDamage2Button: return hasResources(player, SiegeDamage2);
            default: return false;
        }
    }
}
