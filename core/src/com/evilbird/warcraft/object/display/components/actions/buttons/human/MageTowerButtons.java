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

import static com.evilbird.warcraft.data.upgrade.Upgrade.BlizzardUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.FlameShieldUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.InvisibilityUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.PolymorphUpgrade;
import static com.evilbird.warcraft.data.upgrade.Upgrade.SlowUpgrade;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BlizzardUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FlameShieldUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.InvisibilityUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MageButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PolymorphUpgradeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.SlowUpgradeButton;
import static com.evilbird.warcraft.object.unit.UnitType.Mage;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Mage Tower is selected.
 *
 * @author Blair Butterworth
 */
public class MageTowerButtons extends BasicButtonController
{
    private static final List<ActionButtonType> basicButtons = singletonList(MageButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        List<ActionButtonType> buttons = new ArrayList<>(basicButtons);
        addUpgradeButton(player, buttons, SlowUpgradeButton, SlowUpgrade);
        addUpgradeButton(player, buttons, FlameShieldUpgradeButton, FlameShieldUpgrade);
        addUpgradeButton(player, buttons, InvisibilityUpgradeButton, InvisibilityUpgrade);
        addUpgradeButton(player, buttons, PolymorphUpgradeButton, PolymorphUpgrade);
        addUpgradeButton(player, buttons, BlizzardUpgradeButton, BlizzardUpgrade);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case MageButton: return hasResources(player, Mage);
            case SlowUpgradeButton: return hasResources(player, SlowUpgrade);
            case FlameShieldUpgradeButton: return hasResources(player, FlameShieldUpgrade);
            case InvisibilityUpgradeButton: return hasResources(player, InvisibilityUpgrade);
            case PolymorphUpgradeButton: return hasResources(player, PolymorphUpgrade);
            case BlizzardUpgradeButton: return hasResources(player, BlizzardUpgrade);
            default: return false;
        }
    }
}
