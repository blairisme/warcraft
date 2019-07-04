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
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildEncampmentButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildGreatHallButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildPigFarmButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildTrollLumberMillButton;
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
public class PeonSimpleBuildings implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (player.getLevel()) {
            case 1: return asList(
                    BuildPigFarmButton, BuildEncampmentButton, BuildGreatHallButton,
                    BuildCancelButton);
            case 2:
            case 3: return asList(
                    BuildPigFarmButton, BuildEncampmentButton, BuildGreatHallButton,
                    BuildTrollLumberMillButton, BuildCancelButton);
            default: return Collections.emptyList();
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case BuildCancelButton: return true;
            case BuildEncampmentButton: return hasResources(player, Encampment);
            case BuildGreatHallButton: return hasResources(player, GreatHall);
            case BuildPigFarmButton: return hasResources(player, PigFarm);
            case BuildTrollLumberMillButton: return hasResources(player, TrollLumberMill);
            default: return false;
        }
    }
}
