/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.orc;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.BasicButtonController;

import java.util.List;

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PeonButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.StrongholdButton;
import static com.evilbird.warcraft.object.unit.UnitType.Encampment;
import static com.evilbird.warcraft.object.unit.UnitType.Peon;
import static com.evilbird.warcraft.object.unit.UnitType.Stronghold;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when an Orc Great Hall is selected.
 *
 * @author Blair Butterworth
 */
public class GreatHallButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        singletonList(PeonButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
        asList(PeonButton, StrongholdButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return player.getLevel() <= 5 ? BASIC_BUTTONS : ADVANCED_BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case PeonButton: return hasResources(player, Peon);
            case StrongholdButton: return hasResources(player, Stronghold) && hasUnit(player, Encampment);
            default: return false;
        }
    }
}
