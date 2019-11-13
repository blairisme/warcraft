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
import com.evilbird.warcraft.object.display.control.actions.buttons.BasicButtonController;

import java.util.List;

import static com.evilbird.warcraft.object.unit.UnitType.GnomishFlyingMachine;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Gnomish Inventor is selected.
 *
 * @author Blair Butterworth
 */
public class GnomishInventorButtons extends BasicButtonController
{
    private static final List<com.evilbird.warcraft.object.display.control.actions.ActionButtonType> BUTTONS = singletonList(com.evilbird.warcraft.object.display.control.actions.ActionButtonType.GnomishFlyingMachineButton);

    @Override
    public List<com.evilbird.warcraft.object.display.control.actions.ActionButtonType> getButtons(GameObject gameObject) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(com.evilbird.warcraft.object.display.control.actions.ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return hasResources(player, GnomishFlyingMachine);
    }
}
