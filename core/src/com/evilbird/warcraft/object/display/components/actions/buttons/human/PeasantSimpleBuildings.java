/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.human;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.BasicButtonController;

import java.util.List;

import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BarracksButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BlacksmithButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FarmButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.LumberMillButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ScoutTowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TownHallButton;
import static com.evilbird.warcraft.object.unit.UnitType.Barracks;
import static com.evilbird.warcraft.object.unit.UnitType.Blacksmith;
import static com.evilbird.warcraft.object.unit.UnitType.Farm;
import static com.evilbird.warcraft.object.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.object.unit.UnitType.ScoutTower;
import static com.evilbird.warcraft.object.unit.UnitType.TownHall;
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
        asList(FarmButton, BarracksButton, TownHallButton,
               BuildCancelButton);

    private static final List<ActionButtonType> INTERMEDIATE_BUTTONS =
        asList(FarmButton, BarracksButton, TownHallButton,
               LumberMillButton, BuildCancelButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
        asList(FarmButton, BarracksButton, TownHallButton,
               LumberMillButton, BlacksmithButton, ScoutTowerButton,
               BuildCancelButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
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
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case BuildCancelButton: return true;
            case FarmButton: return hasResources(player, Farm);
            case BarracksButton: return hasResources(player, Barracks);
            case LumberMillButton: return hasResources(player, LumberMill);
            case TownHallButton: return hasResources(player, TownHall);
            case BlacksmithButton: return hasResources(player, Blacksmith);
            case ScoutTowerButton: return hasResources(player, ScoutTower);
            default: return false;
        }
    }
}
