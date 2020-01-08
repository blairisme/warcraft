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

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FerryButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GiantTurtleButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OgreJuggernaughtButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TrollDestroyerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TrollTankerButton;
import static com.evilbird.warcraft.object.unit.UnitType.Ferry;
import static com.evilbird.warcraft.object.unit.UnitType.GiantTurtle;
import static com.evilbird.warcraft.object.unit.UnitType.GoblinAlchemist;
import static com.evilbird.warcraft.object.unit.UnitType.Metalworks;
import static com.evilbird.warcraft.object.unit.UnitType.OgreJuggernaught;
import static com.evilbird.warcraft.object.unit.UnitType.TrollDestroyer;
import static com.evilbird.warcraft.object.unit.UnitType.TrollTanker;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when an Orc Dockyard is selected.
 *
 * @author Blair Butterworth
 */
public class DockyardButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
            asList(TrollTankerButton, TrollDestroyerButton);

    private static final List<ActionButtonType> INTERMEDIATE_BUTTONS =
            asList(TrollTankerButton, TrollDestroyerButton, FerryButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
            asList(TrollTankerButton, TrollDestroyerButton, FerryButton,
                    OgreJuggernaughtButton, GiantTurtleButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return getButtons(player.getLevel());
    }

    private List<ActionButtonType> getButtons(int level) {
        if (level == 3) {
            return BASIC_BUTTONS;
        }
        if (level > 3 && level <= 9) {
            return INTERMEDIATE_BUTTONS;
        }
        if (level > 9) {
            return ADVANCED_BUTTONS;
        }
        return Collections.emptyList();
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case TrollTankerButton: return hasResources(player, TrollTanker);
            case TrollDestroyerButton: return hasResources(player, TrollDestroyer);
            case FerryButton: return hasResources(player, Ferry) && hasUnit(player, Metalworks);
            case OgreJuggernaughtButton: return hasResources(player, OgreJuggernaught) && hasUnit(player, Metalworks);
            case GiantTurtleButton: return hasResources(player,GiantTurtle) && hasUnit(player, GoblinAlchemist);
            default: return false;
        }
    }
}
