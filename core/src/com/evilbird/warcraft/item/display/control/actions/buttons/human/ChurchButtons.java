/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.human;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUpgrade;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.ExorcismUpgrade;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.HealingUpgrade;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeType1;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Church is selected.
 *
 * @author Blair Butterworth
 */
public class ChurchButtons extends BasicButtonController
{
    private static final List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> basicButtons = singletonList(com.evilbird.warcraft.item.display.control.actions.ActionButtonType.MeleeType1Button);

    @Override
    public List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return !hasUpgrade(player, MeleeType1) ? basicButtons : getAdvancedButtons(player);
    }

    private List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> getAdvancedButtons(Player player) {
        List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> buttons = new ArrayList<>();
        addUpgradeButton(player, buttons, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.ExorcismUpgradeButton, ExorcismUpgrade);
        addUpgradeButton(player, buttons, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.HealingUpgradeButton, HealingUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(com.evilbird.warcraft.item.display.control.actions.ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case MeleeType1Button: return hasResources(player, MeleeType1);
            case ExorcismUpgradeButton: return hasResources(player, ExorcismUpgrade);
            case HealingUpgradeButton: return hasResources(player, HealingUpgrade);
            default: return false;
        }
    }
}
