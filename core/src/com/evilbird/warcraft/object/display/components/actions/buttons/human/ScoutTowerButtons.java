/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.human;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.BasicButtonController;

import java.util.Arrays;
import java.util.List;

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CannonTowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GuardTowerButton;
import static com.evilbird.warcraft.object.unit.UnitType.Blacksmith;
import static com.evilbird.warcraft.object.unit.UnitType.CannonTower;
import static com.evilbird.warcraft.object.unit.UnitType.GuardTower;
import static com.evilbird.warcraft.object.unit.UnitType.LumberMill;

/**
 * Controls the buttons shown when a Human Scout Tower is selected.
 *
 * @author Blair Butterworth
 */
public class ScoutTowerButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BUTTONS =
        Arrays.asList(GuardTowerButton, CannonTowerButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case GuardTowerButton: return hasResources(player, GuardTower) && hasUnit(player, LumberMill);
            case CannonTowerButton: return hasResources(player, CannonTower) && hasUnit(player, Blacksmith);
            default: return false;
        }
    }
}
