/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.common;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.ButtonController;

import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUpgrade;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.RangedDamage1;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.RangedDamage2;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedRangedUpgradeButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedUpgradeButton;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Lumber Mill is selected.
 *
 * @author Blair Butterworth
 */
public class LumberMillButtons implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        int level = player.getLevel();
        if (level == 1) {
            return ! hasUpgrade(player, RangedDamage1)
                ? singletonList(AdvancedRangedUpgradeButton)
                : emptyList();
        }
        if (level > 1) {
            return ! hasUpgrade(player, RangedDamage1)
                ? singletonList(ImprovedRangedUpgradeButton)
                : singletonList(AdvancedRangedUpgradeButton);
        }
        return emptyList();
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case ImprovedRangedUpgradeButton: return hasResources(player, RangedDamage1);
            case AdvancedRangedUpgradeButton: return hasResources(player, RangedDamage2);
            default: return false;
        }
    }
}
