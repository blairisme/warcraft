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

import static com.evilbird.warcraft.data.upgrade.Upgrade.DeathAndDecayUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.HasteUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RaiseTheDeadUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.UnholyArmourUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.WhirlwindUpgrade;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DeathAndDecayUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DeathKnightButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.HasteUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RaiseTheDeadUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.UnholyArmourUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.WhirlwindUpgradeButton;
import static com.evilbird.warcraft.object.unit.UnitType.Mage;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Orc Temple of the Damned is selected.
 *
 * @author Blair Butterworth
 */
public class TempleOfTheDamnedButtons extends BasicButtonController
{
    private static final List<ActionButtonType> basicButtons = singletonList(DeathKnightButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        List<ActionButtonType> buttons = new ArrayList<>(basicButtons);
        addUpgradeButton(player, buttons, HasteUpgradeButton, HasteUpgrade);
        addUpgradeButton(player, buttons, RaiseTheDeadUpgradeButton, RaiseTheDeadUpgrade);
        addUpgradeButton(player, buttons, WhirlwindUpgradeButton, WhirlwindUpgrade);
        addUpgradeButton(player, buttons, UnholyArmourUpgradeButton, UnholyArmourUpgrade);
        addUpgradeButton(player, buttons, DeathAndDecayUpgradeButton, DeathAndDecayUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case DeathKnightButton: return hasResources(player, Mage);
            case DeathAndDecayUpgradeButton: return hasResources(player, DeathAndDecayUpgrade);
            case HasteUpgradeButton: return hasResources(player, HasteUpgrade);
            case RaiseTheDeadUpgradeButton: return hasResources(player, RaiseTheDeadUpgrade);
            case UnholyArmourUpgradeButton: return hasResources(player, UnholyArmourUpgrade);
            case WhirlwindUpgradeButton: return hasResources(player, WhirlwindUpgrade);
            default: return false;
        }
    }
}
