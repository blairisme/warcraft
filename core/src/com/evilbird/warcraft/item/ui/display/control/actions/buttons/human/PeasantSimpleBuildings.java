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

import java.util.List;

import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildBarracksButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildBlacksmithButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildFarmButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildLumberMillButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildScoutTowerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildTownHallButton;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Blacksmith;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.ScoutTower;
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
    private static final List<ActionButtonType> BASIC_BUTTONS =
        asList(BuildFarmButton, BuildBarracksButton, BuildTownHallButton,
            BuildCancelButton);

    private static final List<ActionButtonType> INTERMEDIATE_BUTTONS =
        asList(BuildFarmButton, BuildBarracksButton, BuildTownHallButton,
            BuildLumberMillButton, BuildCancelButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
        asList(BuildFarmButton, BuildBarracksButton, BuildTownHallButton,
            BuildLumberMillButton, BuildBlacksmithButton, BuildScoutTowerButton,
            BuildCancelButton);

    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        return getButtons(player.getLevel());
    }

    private List<ActionButtonType> getButtons(int level) {
        if (level == 1) {
            return BASIC_BUTTONS;
        }
        if (level == 2 || level == 3) {
            return INTERMEDIATE_BUTTONS;
        }
        return ADVANCED_BUTTONS;
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
            case BuildBlacksmithButton: return hasResources(player, Blacksmith);
            case BuildScoutTowerButton: return hasResources(player, ScoutTower);
            default: return false;
        }
    }
}
