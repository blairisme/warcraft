/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.BattleshipButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.ElvenDestroyerButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.GnomishSubmarineButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.OilTankerButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.TransportButton;
import static com.evilbird.warcraft.item.unit.UnitType.Battleship;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.Foundry;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishInventor;
import static com.evilbird.warcraft.item.unit.UnitType.OilTanker;
import static com.evilbird.warcraft.item.unit.UnitType.Transport;
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
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
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
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case OilTankerButton: return hasResources(player, OilTanker);
            case ElvenDestroyerButton: return hasResources(player, ElvenDestroyer);
            case TransportButton: return hasResources(player, Transport) && hasUnit(player, Foundry);
            case BattleshipButton: return hasResources(player, Battleship) && hasUnit(player, Foundry);
            case GnomishSubmarineButton: return hasResources(player,Battleship) && hasUnit(player,GnomishInventor);
            default: return false;
        }
    }
}
