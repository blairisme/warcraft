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

import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeDamage1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeDamage2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeDefence1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.MeleeDefence2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.SiegeDamage1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.SiegeDamage2;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MeleeDamage1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MeleeDamage2Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MeleeDefence1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MeleeDefence2Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.SiegeDamage1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.SiegeDamage2Button;

/**
 * Controls the buttons shown when a Human Blacksmith is selected.
 *
 * @author Blair Butterworth
 */
public class BlacksmithButtons extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return player.getLevel() <= 5 ? getBasicUpgrades(player) : getAdvancedUpgrades(player);
    }

    private List<ActionButtonType> getBasicUpgrades(Player player) {
        List<ActionButtonType> buttons = new ArrayList<>();
        addUpgradeButton(player, buttons, MeleeDamage1Button, MeleeDamage1);
        addUpgradeButton(player, buttons, MeleeDefence1Button, MeleeDefence1);
        addUpgradeButton(player, buttons, SiegeDamage1Button, SiegeDamage1);
        return buttons;
    }

    private List<ActionButtonType> getAdvancedUpgrades(Player player) {
        List<ActionButtonType> buttons = getBasicUpgrades(player);
        addUpgradeButton(player, buttons, MeleeDamage2Button, MeleeDamage1, MeleeDamage2);
        addUpgradeButton(player, buttons, MeleeDefence2Button, MeleeDefence1, MeleeDefence2);
        addUpgradeButton(player, buttons, SiegeDamage2Button, SiegeDamage1, SiegeDamage2);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case MeleeDamage1Button: return hasResources(player, MeleeDamage1);
            case MeleeDamage2Button: return hasResources(player, MeleeDamage2);
            case MeleeDefence1Button: return hasResources(player, MeleeDefence1);
            case MeleeDefence2Button: return hasResources(player, MeleeDefence2);
            case SiegeDamage1Button: return hasResources(player, SiegeDamage1);
            case SiegeDamage2Button: return hasResources(player, SiegeDamage2);
            default: return false;
        }
    }
}
