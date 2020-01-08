/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
