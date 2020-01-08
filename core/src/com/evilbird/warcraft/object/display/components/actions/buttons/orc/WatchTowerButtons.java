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

import java.util.Arrays;
import java.util.List;

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BombardTowerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.LookoutTowerButton;
import static com.evilbird.warcraft.object.unit.UnitType.BombardTower;
import static com.evilbird.warcraft.object.unit.UnitType.Encampment;
import static com.evilbird.warcraft.object.unit.UnitType.LookoutTower;
import static com.evilbird.warcraft.object.unit.UnitType.TrollLumberMill;

/**
 * Controls the buttons shown when a Orc Watch Tower is selected.
 *
 * @author Blair Butterworth
 */
public class WatchTowerButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BUTTONS =
        Arrays.asList(LookoutTowerButton, BombardTowerButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case LookoutTowerButton: return hasResources(player, LookoutTower) && hasUnit(player, TrollLumberMill);
            case BombardTowerButton: return hasResources(player, BombardTower) && hasUnit(player, Encampment);
            default: return false;
        }
    }
}
