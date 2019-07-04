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

import java.util.Arrays;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildOilPlatformButton;
import static com.evilbird.warcraft.item.unit.UnitType.OilPlatform;

/**
 * Controls the buttons shown when a Human Oil Tanker is selected and the user
 * navigates to the simple building menu.
 *
 * @author Blair Butterworth
 */
public class OilTankerBuildings implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        return Arrays.asList(BuildOilPlatformButton, BuildCancelButton);
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case BuildCancelButton: return true;
            case BuildOilPlatformButton: return hasResources(player, OilPlatform);
            default: return false;
        }
    }
}
