/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.ButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUpgrade;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.AdvancedAxeDamage;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.BasicAxeDamage;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.AdvancedRangedUpgradeButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedUpgradeButton;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when an Orc Troll Lumber Mill is selected.
 *
 * @author Blair Butterworth
 */
public class TrollLumberMillButtons implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (player.getLevel()) {
            case 2: return singletonList(ImprovedRangedUpgradeButton);
            case 3: return asList(ImprovedRangedUpgradeButton, AdvancedRangedUpgradeButton);
            default: return Collections.emptyList();
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case ImprovedRangedUpgradeButton: return hasRequirements(player, BasicAxeDamage);
            case AdvancedRangedUpgradeButton: return hasRequirements(player, AdvancedAxeDamage, BasicAxeDamage);
            default: return false;
        }
    }

    private boolean hasRequirements(Player player, PlayerUpgrade upgrade) {
        return hasResources(player, upgrade);
    }

    private boolean hasRequirements(Player player, PlayerUpgrade upgrade, PlayerUpgrade prerequisite) {
        return hasResources(player, upgrade) && hasUpgrade(player, prerequisite);
    }
}
