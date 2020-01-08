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

import java.util.List;

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.KeepButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PeasantButton;
import static com.evilbird.warcraft.object.unit.UnitType.Barracks;
import static com.evilbird.warcraft.object.unit.UnitType.Keep;
import static com.evilbird.warcraft.object.unit.UnitType.Peasant;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Town Hall is selected.
 *
 * @author Blair Butterworth
 */
public class TownHallButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        singletonList(PeasantButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
        asList(PeasantButton, KeepButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return player.getLevel() <= 5 ? BASIC_BUTTONS : ADVANCED_BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case PeasantButton: return hasResources(player, Peasant);
            case KeepButton: return hasResources(player, Keep) && hasUnit(player, Barracks);
            default: return false;
        }
    }
}
