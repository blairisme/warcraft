/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.ButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainElvenDestroyerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainOilTankerButton;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.OilTanker;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Human Shipyard is selected.
 *
 * @author Blair Butterworth
 */
public class ShipyardButtons implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (player.getLevel()) {
            case 3: return asList(TrainOilTankerButton, TrainElvenDestroyerButton);
            default: return Collections.emptyList();
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case TrainOilTankerButton: return hasResources(player, OilTanker);
            case TrainElvenDestroyerButton: return hasResources(player, ElvenDestroyer);
            default: return false;
        }
    }
}
