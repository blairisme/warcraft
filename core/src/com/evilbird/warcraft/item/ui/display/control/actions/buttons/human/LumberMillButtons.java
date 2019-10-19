/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.BasicButtonController;

import java.util.ArrayList;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUpgrade;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedAccuracy1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedSight1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedType1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedWeapon1;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedRangedDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedAccuracyButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedDamageButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedSightButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedTypeButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedWeaponButton;

/**
 * Controls the buttons shown when a Human Lumber Mill is selected.
 *
 * @author Blair Butterworth
 */
public class LumberMillButtons extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
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
        addUpgradeButton(player, buttons, ImprovedRangedDamageButton, RangedDamage1);
        return buttons;
    }

    private List<ActionButtonType> getAdvancedArcherButtons(Player player) {
        List<ActionButtonType> buttons = getBasicArcherButtons(player);
        addUpgradeButton(player, buttons, AdvancedRangedDamageButton, RangedDamage1, RangedDamage2);
        return buttons;
    }

    private List<ActionButtonType> getRangerButtons(Player player) {
        return !hasUpgrade(player, RangedType1) ? getBasicRangerButtons(player) : getAdvancedRangerButtons(player);
    }

    private List<ActionButtonType> getBasicRangerButtons(Player player) {
        List<ActionButtonType> buttons = getAdvancedArcherButtons(player);
        buttons.add(ImprovedRangedTypeButton);
        return buttons;
    }

    private List<ActionButtonType> getAdvancedRangerButtons(Player player) {
        List<ActionButtonType> buttons = getAdvancedArcherButtons(player);
        addUpgradeButton(player, buttons, ImprovedRangedSightButton, RangedSight1);
        addUpgradeButton(player, buttons, ImprovedRangedWeaponButton, RangedWeapon1);
        addUpgradeButton(player, buttons, ImprovedRangedAccuracyButton, RangedAccuracy1);
        return buttons;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case ImprovedRangedDamageButton: return hasResources(player, RangedDamage1);
            case AdvancedRangedDamageButton: return hasResources(player, RangedDamage2);
            case ImprovedRangedTypeButton: return hasResources(player, RangedType1);
            case ImprovedRangedSightButton: return hasResources(player, RangedSight1);
            case ImprovedRangedAccuracyButton: return hasResources(player, RangedWeapon1);
            case ImprovedRangedWeaponButton: return hasResources(player, RangedAccuracy1);
            default: return false;
        }
    }
}
