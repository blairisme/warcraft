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

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.EncampmentButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.GreatHallButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.PigFarmButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrollLumberMillButton;
import static com.evilbird.warcraft.item.unit.UnitType.Encampment;
import static com.evilbird.warcraft.item.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.item.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.item.unit.UnitType.TrollLumberMill;
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
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
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
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
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
