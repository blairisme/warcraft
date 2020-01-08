/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.common;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.ButtonController;

import java.util.List;

import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.DefendButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PatrolButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.StopButton;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a combatant is selected.
 *
 * @author Blair Butterworth
 */
public class CombatantButtons implements ButtonController
{
    private static final List<ActionButtonType> BUTTONS =
        asList(MoveButton, StopButton, AttackButton, PatrolButton, DefendButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        return button == StopButton;
    }
}
