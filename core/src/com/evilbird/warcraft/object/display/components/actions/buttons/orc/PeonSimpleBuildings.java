/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.orc;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.BasicButtonController;

import java.util.List;

import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.EncampmentButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ForgeButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GreatHallButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PigFarmButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TrollLumberMillButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.WatchTowerButton;
import static com.evilbird.warcraft.object.unit.UnitType.Encampment;
import static com.evilbird.warcraft.object.unit.UnitType.Forge;
import static com.evilbird.warcraft.object.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.object.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.object.unit.UnitType.TrollLumberMill;
import static com.evilbird.warcraft.object.unit.UnitType.WatchTower;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Peon is selected and the user navigates
 * to the simple building menu.
 *
 * @author Blair Butterworth
 */
public class PeonSimpleBuildings extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        asList(PigFarmButton, EncampmentButton, GreatHallButton,
                BuildCancelButton);

    private static final List<ActionButtonType> INTERMEDIATE_BUTTONS =
        asList(PigFarmButton, EncampmentButton, GreatHallButton,
                TrollLumberMillButton, BuildCancelButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
        asList(PigFarmButton, EncampmentButton, GreatHallButton,
                TrollLumberMillButton, ForgeButton, WatchTowerButton,
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
            case PigFarmButton: return hasResources(player, PigFarm);
            case EncampmentButton: return hasResources(player, Encampment);
            case TrollLumberMillButton: return hasResources(player, TrollLumberMill);
            case GreatHallButton: return hasResources(player, GreatHall);
            case ForgeButton: return hasResources(player, Forge);
            case WatchTowerButton: return hasResources(player, WatchTower);
            default: return false;
        }
    }
}
