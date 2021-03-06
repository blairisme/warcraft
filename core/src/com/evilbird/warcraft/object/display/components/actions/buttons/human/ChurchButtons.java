/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.human;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.data.upgrade.Upgrade.ExorcismUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.HealingUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeType1;
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
        addUpgradeButton(player, buttons, ActionButtonType.ExorcismUpgradeButton, ExorcismUpgrade);
        addUpgradeButton(player, buttons, ActionButtonType.HealingUpgradeButton, HealingUpgrade);
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
