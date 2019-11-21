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

import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.GoblinSappersButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.GoblinZeppelinButton;
import static com.evilbird.warcraft.object.unit.UnitType.GoblinSappers;
import static com.evilbird.warcraft.object.unit.UnitType.GoblinZeppelin;

/**
 * Controls the buttons shown when a Human Gnomish Inventor is selected.
 *
 * @author Blair Butterworth
 */
public class GoblinAlchemistButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BUTTONS =
        Arrays.asList(GoblinZeppelinButton, GoblinSappersButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case GoblinZeppelinButton: return hasResources(player, GoblinZeppelin);
            case GoblinSappersButton: return hasResources(player, GoblinSappers);
            default: return true;
        }
    }
}
