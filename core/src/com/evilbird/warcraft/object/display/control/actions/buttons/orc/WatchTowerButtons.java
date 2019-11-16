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

import java.util.Arrays;
import java.util.List;

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.BombardTowerButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.LookoutTowerButton;
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
