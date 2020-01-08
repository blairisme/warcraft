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

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnits;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.CastleButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PeasantButton;
import static com.evilbird.warcraft.object.unit.UnitType.Barracks;
import static com.evilbird.warcraft.object.unit.UnitType.Blacksmith;
import static com.evilbird.warcraft.object.unit.UnitType.Castle;
import static com.evilbird.warcraft.object.unit.UnitType.Peasant;
import static com.evilbird.warcraft.object.unit.UnitType.Stables;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Keep is selected.
 *
 * @author Blair Butterworth
 */
public class KeepButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        singletonList(PeasantButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
        asList(PeasantButton, CastleButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return player.getLevel() <= 10 ? BASIC_BUTTONS : ADVANCED_BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);

        if (button == PeasantButton) {
            return hasResources(player, Peasant);
        }
        if (button == CastleButton) {
            return hasResources(player, Castle) && hasUnits(player, Barracks, Blacksmith, Stables);
        }
        return false;
    }
}
