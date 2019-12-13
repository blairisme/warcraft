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

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.data.upgrade.Upgrade.BloodlustUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeType1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RunesUpgrade;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.BloodlustUpgradeButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.MeleeType1Button;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.RunesUpgradeButton;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Orc Altar of Storms is selected.
 *
 * @author Blair Butterworth
 */
public class AltarOfStormsButtons extends BasicButtonController
{
    private static final List<ActionButtonType> basicButtons = singletonList(MeleeType1Button);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return !player.hasUpgrade(MeleeType1) ? basicButtons : getAdvancedButtons(player);
    }

    private List<ActionButtonType> getAdvancedButtons(Player player) {
        List<ActionButtonType> buttons = new ArrayList<>();
        addUpgradeButton(player, buttons, BloodlustUpgradeButton, BloodlustUpgrade);
        addUpgradeButton(player, buttons, RunesUpgradeButton, RunesUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case MeleeType1Button: return hasResources(player, MeleeType1);
            case BloodlustUpgradeButton: return hasResources(player, BloodlustUpgrade);
            case RunesUpgradeButton: return hasResources(player, RunesUpgrade);
            default: return false;
        }
    }
}
