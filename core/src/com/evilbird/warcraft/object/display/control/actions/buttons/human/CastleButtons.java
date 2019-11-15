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

import java.util.List;

import static com.evilbird.warcraft.object.unit.UnitType.Peasant;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Keep is selected.
 *
 * @author Blair Butterworth
 */
public class CastleButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BUTTONS =
        singletonList(ActionButtonType.PeasantButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return hasResources(player, Peasant);
    }
}
