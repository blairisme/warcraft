/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;

import java.util.List;

import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.OilPlatformButton;
import static com.evilbird.warcraft.item.unit.UnitType.OilPlatform;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Human Oil Tanker is selected and the user
 * navigates to the simple building menu.
 *
 * @author Blair Butterworth
 */
public class OilTankerBuildings extends BasicButtonController
{
    private static final List<ActionButtonType> BUTTONS =
        asList(OilPlatformButton, BuildCancelButton);

    @Override
    public List<ActionButtonType> getButtons(Item item) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);

        if (button == BuildCancelButton) {
            return true;
        }
        if (button == OilPlatformButton) {
            return hasResources(player, OilPlatform);
        }
        return false;
    }
}
