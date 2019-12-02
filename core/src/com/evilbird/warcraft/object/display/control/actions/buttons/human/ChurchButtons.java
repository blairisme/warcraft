/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions.buttons.human;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.control.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.object.common.upgrade.Upgrade.ExorcismUpgrade;
import static com.evilbird.warcraft.object.common.upgrade.Upgrade.HealingUpgrade;
import static com.evilbird.warcraft.object.common.upgrade.Upgrade.MeleeType1;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.ExorcismUpgradeButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.HealingUpgradeButton;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Church is selected.
 *
 * @author Blair Butterworth
 */
public class ChurchButtons extends BasicButtonController
{
    private static final List<ActionButtonType> basicButtons = singletonList(ActionButtonType.MeleeType1Button);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return !player.hasUpgrade(MeleeType1) ? basicButtons : getAdvancedButtons(player);
    }

    private List<ActionButtonType> getAdvancedButtons(Player player) {
        List<ActionButtonType> buttons = new ArrayList<>();
        addUpgradeButton(player, buttons, ExorcismUpgradeButton, ExorcismUpgrade);
        addUpgradeButton(player, buttons, HealingUpgradeButton, HealingUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case MeleeType1Button: return hasResources(player, MeleeType1);
            case ExorcismUpgradeButton: return hasResources(player, ExorcismUpgrade);
            case HealingUpgradeButton: return hasResources(player, HealingUpgrade);
            default: return false;
        }
    }
}
