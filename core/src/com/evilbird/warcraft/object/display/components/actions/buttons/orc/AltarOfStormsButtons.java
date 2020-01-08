/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.orc;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.data.upgrade.Upgrade.BloodlustUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeType1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RunesUpgrade;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BloodlustUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MeleeType1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RunesUpgradeButton;
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
