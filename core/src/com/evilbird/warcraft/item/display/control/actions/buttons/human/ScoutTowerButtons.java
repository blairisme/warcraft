/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.human;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;

import java.util.Arrays;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.item.unit.UnitType.Blacksmith;
import static com.evilbird.warcraft.item.unit.UnitType.CannonTower;
import static com.evilbird.warcraft.item.unit.UnitType.GuardTower;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;

/**
 * Controls the buttons shown when a Human Scout Tower is selected.
 *
 * @author Blair Butterworth
 */
public class ScoutTowerButtons extends BasicButtonController
{
    private static final List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> BUTTONS =
        Arrays.asList(com.evilbird.warcraft.item.display.control.actions.ActionButtonType.GuardTowerButton, com.evilbird.warcraft.item.display.control.actions.ActionButtonType.CannonTowerButton);

    @Override
    public List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> getButtons(GameObject gameObject) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(com.evilbird.warcraft.item.display.control.actions.ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case GuardTowerButton: return hasResources(player, GuardTower) && hasUnit(player, LumberMill);
            case CannonTowerButton: return hasResources(player, CannonTower) && hasUnit(player, Blacksmith);
            default: return false;
        }
    }
}
