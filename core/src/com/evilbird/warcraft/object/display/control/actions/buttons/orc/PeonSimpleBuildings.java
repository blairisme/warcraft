/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions.buttons.orc;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.control.actions.buttons.BasicButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.EncampmentButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.GreatHallButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.PigFarmButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.TrollLumberMillButton;
import static com.evilbird.warcraft.object.unit.UnitType.Encampment;
import static com.evilbird.warcraft.object.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.object.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.object.unit.UnitType.TrollLumberMill;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Peon is selected and the user navigates
 * to the simple building menu.
 *
 * @author Blair Butterworth
 */
public class PeonSimpleBuildings extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (player.getLevel()) {
            case 1: return asList(
                    PigFarmButton, EncampmentButton, GreatHallButton,
                    BuildCancelButton);
            case 2:
            case 3: return asList(
                    PigFarmButton, EncampmentButton, GreatHallButton,
                    TrollLumberMillButton, BuildCancelButton);
            default: return Collections.emptyList();
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case BuildCancelButton: return true;
            case EncampmentButton: return hasResources(player, Encampment);
            case GreatHallButton: return hasResources(player, GreatHall);
            case PigFarmButton: return hasResources(player, PigFarm);
            case TrollLumberMillButton: return hasResources(player, TrollLumberMill);
            default: return false;
        }
    }
}
