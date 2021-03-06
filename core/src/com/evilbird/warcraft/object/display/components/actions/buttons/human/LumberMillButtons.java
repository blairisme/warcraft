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

import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedAccuracy1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedDamage1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedDamage2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedSight1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedType1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.RangedWeapon1;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RangedAccuracy1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RangedDamage1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RangedDamage2Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RangedSight1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RangedType1Button;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RangedWeapon1Button;

/**
 * Controls the buttons shown when a Human Lumber Mill is selected.
 *
 * @author Blair Butterworth
 */
public class LumberMillButtons extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return getButtons(player);
    }

    private List<ActionButtonType> getButtons(Player player) {
        int level = player.getLevel();

        if (level == 1) {
            return getBasicArcherButtons(player);
        }
        if (level > 1 && level <= 5) {
            return getAdvancedArcherButtons(player);
        }
        return getRangerButtons(player);
    }

    private List<ActionButtonType> getBasicArcherButtons(Player player) {
        List<ActionButtonType> buttons = new ArrayList<>();
        addUpgradeButton(player, buttons, RangedDamage1Button, RangedDamage1);
        return buttons;
    }

    private List<ActionButtonType> getAdvancedArcherButtons(Player player) {
        List<ActionButtonType> buttons = getBasicArcherButtons(player);
        addUpgradeButton(player, buttons, RangedDamage2Button, RangedDamage1, RangedDamage2);
        return buttons;
    }

    private List<ActionButtonType> getRangerButtons(Player player) {
        return !player.hasUpgrade(RangedType1) ? getBasicRangerButtons(player) : getAdvancedRangerButtons(player);
    }

    private List<ActionButtonType> getBasicRangerButtons(Player player) {
        List<ActionButtonType> buttons = getAdvancedArcherButtons(player);
        buttons.add(RangedType1Button);
        return buttons;
    }

    private List<ActionButtonType> getAdvancedRangerButtons(Player player) {
        List<ActionButtonType> buttons = getAdvancedArcherButtons(player);
        addUpgradeButton(player, buttons, RangedSight1Button, RangedSight1);
        addUpgradeButton(player, buttons, RangedWeapon1Button, RangedWeapon1);
        addUpgradeButton(player, buttons, RangedAccuracy1Button, RangedAccuracy1);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case RangedDamage1Button: return hasResources(player, RangedDamage1);
            case RangedDamage2Button: return hasResources(player, RangedDamage2);
            case RangedType1Button: return hasResources(player, RangedType1);
            case RangedSight1Button: return hasResources(player, RangedSight1);
            case RangedAccuracy1Button: return hasResources(player, RangedWeapon1);
            case RangedWeapon1Button: return hasResources(player, RangedAccuracy1);
            default: return false;
        }
    }
}
