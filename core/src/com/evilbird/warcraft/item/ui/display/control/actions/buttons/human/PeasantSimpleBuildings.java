/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.BasicButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildBarracksButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildFarmButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildLumberMillButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildTownHallButton;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Peasant is selected and the user navigates
 * to the simple building menu.
 *
 * @author Blair Butterworth
 */
public class PeasantSimpleBuildings extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (player.getLevel()) {
            case 1: return asList(
                    BuildFarmButton, BuildBarracksButton, BuildTownHallButton,
                    BuildCancelButton);
            case 2:
            case 3: return asList(
                    BuildFarmButton, BuildBarracksButton, BuildTownHallButton,
                    BuildLumberMillButton, BuildCancelButton);
            default: return Collections.emptyList();
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case BuildCancelButton: return true;
            case BuildFarmButton: return hasResources(player, Farm);
            case BuildBarracksButton: return hasResources(player, Barracks);
            case BuildLumberMillButton: return hasResources(player, LumberMill);
            case BuildTownHallButton: return hasResources(player, TownHall);
            default: return false;
        }
    }
}
