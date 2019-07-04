/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.actions.buttons.orc;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.hud.control.actions.buttons.ButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildDockyardButton;
import static com.evilbird.warcraft.item.unit.UnitType.Dockyard;
import static com.evilbird.warcraft.item.unit.UnitType.TrollLumberMill;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Peon is selected and the user navigates
 * to the advanced building menu.
 *
 * @author Blair Butterworth
 */
public class PeonAdvancedBuildings implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (player.getLevel()) {
            case 3: return asList(
                    BuildDockyardButton, BuildCancelButton);
            default: return Collections.emptyList();
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case BuildCancelButton: return true;
            case BuildDockyardButton: return hasResources(player, Dockyard) && hasUnit(player, TrollLumberMill);
            default: return false;
        }
    }
}
