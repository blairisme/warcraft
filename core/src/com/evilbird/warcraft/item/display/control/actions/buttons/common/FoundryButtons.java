/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.common;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDefence1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDefence2;

/**
 * Controls the buttons shown when a Human Foundry is selected.
 *
 * @author Blair Butterworth
 */
public class FoundryButtons extends BasicButtonController
{
    @Override
    public List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return player.getLevel() <= 5 ? getBasicUpgrades(player) : getAdvancedUpgrades(player);
    }

    private List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> getBasicUpgrades(Player player) {
        List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> buttons = new ArrayList<>();
        addUpgradeButton(player, buttons, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.NavalDamage1Button, NavalDamage1);
        addUpgradeButton(player, buttons, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.NavalDefence1Button, NavalDefence1);
        return buttons;
    }

    private List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> getAdvancedUpgrades(Player player) {
        List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> buttons = getBasicUpgrades(player);
        addUpgradeButton(player, buttons, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.NavalDamage2Button, NavalDamage1, NavalDamage2);
        addUpgradeButton(player, buttons, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.NavalDefence2Button, NavalDefence1, NavalDefence2);
        return buttons;
    }

    @Override
    public boolean getEnabled(com.evilbird.warcraft.item.display.control.actions.ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case NavalDamage1Button: return hasResources(player, NavalDamage1);
            case NavalDamage2Button: return hasResources(player, NavalDamage2);
            case NavalDefence1Button: return hasResources(player, NavalDefence1);
            case NavalDefence2Button: return hasResources(player, NavalDefence2);
            default: return false;
        }
    }
}
