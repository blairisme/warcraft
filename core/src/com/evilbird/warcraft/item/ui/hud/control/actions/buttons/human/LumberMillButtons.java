/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.hud.control.actions.buttons.ButtonController;

import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.ArrowDamage;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.UpgradeArrowDamageButton;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Lumber Mill is selected.
 *
 * @author Blair Butterworth
 */
public class LumberMillButtons implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (player.getLevel()) {
            case 1: return emptyList();
            default: return singletonList(UpgradeArrowDamageButton);
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case UpgradeArrowDamageButton: return hasResources(player, ArrowDamage);
            default: return false;
        }
    }
}
