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

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BattleshipButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.ElvenDestroyerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.GnomishSubmarineButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.OilTankerButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.TransportButton;
import static com.evilbird.warcraft.object.unit.UnitType.Battleship;
import static com.evilbird.warcraft.object.unit.UnitType.ElvenDestroyer;
import static com.evilbird.warcraft.object.unit.UnitType.Foundry;
import static com.evilbird.warcraft.object.unit.UnitType.GnomishInventor;
import static com.evilbird.warcraft.object.unit.UnitType.GnomishSubmarine;
import static com.evilbird.warcraft.object.unit.UnitType.OilTanker;
import static com.evilbird.warcraft.object.unit.UnitType.Transport;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Human Shipyard is selected.
 *
 * @author Blair Butterworth
 */
public class ShipyardButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        asList(OilTankerButton, ElvenDestroyerButton);

    private static final List<ActionButtonType> INTERMEDIATE_BUTTONS =
        asList(OilTankerButton, ElvenDestroyerButton, TransportButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
        asList(OilTankerButton, ElvenDestroyerButton, TransportButton,
                BattleshipButton, GnomishSubmarineButton);

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
            case OilTankerButton: return hasResources(player, OilTanker);
            case ElvenDestroyerButton: return hasResources(player, ElvenDestroyer);
            case TransportButton: return hasResources(player, Transport) && hasUnit(player, Foundry);
            case BattleshipButton: return hasResources(player, Battleship) && hasUnit(player, Foundry);
            case GnomishSubmarineButton:return hasResources(player,GnomishSubmarine) && hasUnit(player,GnomishInventor);
            default: return false;
        }
    }
}
