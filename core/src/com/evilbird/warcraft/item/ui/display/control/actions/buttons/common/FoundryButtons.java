/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.common;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDefence1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDefence2;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.NavalDamage1Button;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.NavalDamage2Button;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.NavalDefence1Button;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.NavalDefence2Button;

/**
 * Controls the buttons shown when a Human Foundry is selected.
 *
 * @author Blair Butterworth
 */
public class FoundryButtons extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        return player.getLevel() <= 5 ? getBasicUpgrades(player) : getAdvancedUpgrades(player);
    }

    private List<ActionButtonType> getBasicUpgrades(Player player) {
        List<ActionButtonType> buttons = new ArrayList<>();
        addUpgradeButton(player, buttons, NavalDamage1Button, NavalDamage1);
        addUpgradeButton(player, buttons, NavalDefence1Button, NavalDefence1);
        return buttons;
    }

    private List<ActionButtonType> getAdvancedUpgrades(Player player) {
        List<ActionButtonType> buttons = getBasicUpgrades(player);
        addUpgradeButton(player, buttons, NavalDamage2Button, NavalDamage1, NavalDamage2);
        addUpgradeButton(player, buttons, NavalDefence2Button, NavalDefence1, NavalDefence2);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case NavalDamage1Button: return hasResources(player, NavalDamage1);
            case NavalDamage2Button: return hasResources(player, NavalDamage2);
            case NavalDefence1Button: return hasResources(player, NavalDefence1);
            case NavalDefence2Button: return hasResources(player, NavalDefence2);
            default: return false;
        }
    }
}