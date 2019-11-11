/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.orc;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.DockyardButton;
import static com.evilbird.warcraft.item.unit.UnitType.Dockyard;
import static com.evilbird.warcraft.item.unit.UnitType.TrollLumberMill;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Peon is selected and the user navigates
 * to the advanced building menu.
 *
 * @author Blair Butterworth
 */
public class PeonAdvancedBuildings extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (player.getLevel()) {
            case 3: return asList(
                    DockyardButton, BuildCancelButton);
            default: return Collections.emptyList();
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case BuildCancelButton: return true;
            case DockyardButton: return hasResources(player, Dockyard) && hasUnit(player, TrollLumberMill);
            default: return false;
        }
    }
}
