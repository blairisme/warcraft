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

import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OilPlatformButton;
import static com.evilbird.warcraft.object.unit.UnitType.OilPlatform;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Human Oil Tanker is selected and the user
 * navigates to the simple building menu.
 *
 * @author Blair Butterworth
 */
public class OilTankerBuildings extends BasicButtonController
{
    private static final List<ActionButtonType> BUTTONS =
        asList(OilPlatformButton, BuildCancelButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);

        if (button == BuildCancelButton) {
            return true;
        }
        if (button == OilPlatformButton) {
            return hasResources(player, OilPlatform);
        }
        return false;
    }
}
