/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.common;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.data.upgrade.Upgrade.NavalDamage1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.NavalDamage2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.NavalDefence1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.NavalDefence2;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.NavalDamage1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.NavalDamage2Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.NavalDefence1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.NavalDefence2Button;

/**
 * Controls the buttons shown when a Human Foundry is selected.
 *
 * @author Blair Butterworth
 */
public class FoundryButtons extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
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
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
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
