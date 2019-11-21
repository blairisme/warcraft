/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions.buttons.human;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.control.actions.buttons.BasicButtonController;

import java.util.Arrays;
import java.util.List;

import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.DwarvenDemolitionSquadButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.GnomishFlyingMachineButton;
import static com.evilbird.warcraft.object.unit.UnitType.DwarvenDemolitionSquad;
import static com.evilbird.warcraft.object.unit.UnitType.GnomishFlyingMachine;

/**
 * Controls the buttons shown when a Human Gnomish Inventor is selected.
 *
 * @author Blair Butterworth
 */
public class GnomishInventorButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BUTTONS =
        Arrays.asList(GnomishFlyingMachineButton, DwarvenDemolitionSquadButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case GnomishFlyingMachineButton: return hasResources(player, GnomishFlyingMachine);
            case DwarvenDemolitionSquadButton: return hasResources(player, DwarvenDemolitionSquad);
            default: return true;
        }
    }
}
