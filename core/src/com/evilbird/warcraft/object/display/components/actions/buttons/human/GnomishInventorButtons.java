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

import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DwarvenDemolitionSquadButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GnomishFlyingMachineButton;
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
