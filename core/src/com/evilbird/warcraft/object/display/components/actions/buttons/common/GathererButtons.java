/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.common;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.ButtonController;

import java.util.List;

import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BuildAdvancedButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BuildSimpleButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GatherButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.RepairButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.StopButton;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a gatherer is selected.
 *
 * @author Blair Butterworth
 */
public class GathererButtons implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        return asList(MoveButton, StopButton, AttackButton, RepairButton, GatherButton,
            BuildSimpleButton, BuildAdvancedButton);
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        if (button == StopButton || button == BuildSimpleButton) {
            return true;
        }
        if (button == BuildAdvancedButton) {
            Player player = UnitOperations.getPlayer(gameObject);
            return player.getLevel() > 2;
        }
        return false;
    }
}
