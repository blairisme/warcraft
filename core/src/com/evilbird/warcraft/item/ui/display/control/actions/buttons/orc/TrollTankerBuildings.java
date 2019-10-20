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
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.BasicButtonController;

import java.util.Arrays;
import java.util.List;

import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.OilRigButton;
import static com.evilbird.warcraft.item.unit.UnitType.OilRig;

/**
 * Controls the buttons shown when an Orc Troll Tanker (Oil Tanker) is selected
 * and the user navigates to the simple building menu.
 *
 * @author Blair Butterworth
 */
public class TrollTankerBuildings extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        return Arrays.asList(OilRigButton, BuildCancelButton);
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case BuildCancelButton: return true;
            case OilRigButton: return hasResources(player, OilRig);
            default: return false;
        }
    }
}
