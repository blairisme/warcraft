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
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.BasicButtonController;

import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUpgrade;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage2;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedRangedUpgradeButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedUpgradeButton;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

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
        if (player.getLevel() == 1) {
            return getEarlyLevelButtons(player);
        }
        return getAdvancedLevelButtons(player);
    }

    private List<ActionButtonType> getEarlyLevelButtons(Player player) {
        if (! hasUpgrade(player, RangedDamage1)) {
            return singletonList(ImprovedRangedUpgradeButton);
        }
        return emptyList();
    }

    private List<ActionButtonType> getAdvancedLevelButtons(Player player) {
        if (hasUpgrade(player, RangedDamage2)){
            return emptyList();
        } else if (hasUpgrade(player, RangedDamage1)){
            return singletonList(AdvancedRangedUpgradeButton);
        } else {
            return singletonList(ImprovedRangedUpgradeButton);
        }
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
