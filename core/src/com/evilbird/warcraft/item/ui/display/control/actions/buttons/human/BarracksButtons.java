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
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.BasicButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainElvenArcherButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainFootmanButton;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Barracks is selected.
 *
 * @author Blair Butterworth
 */
public class BarracksButtons extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (player.getLevel()) {
            case 1:
            case 2: return singletonList(TrainFootmanButton);
            case 3: return asList(TrainFootmanButton, TrainElvenArcherButton);
            default: return Collections.emptyList();
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case TrainFootmanButton: return hasResources(player, Footman);
            case TrainElvenArcherButton: return hasResources(player, ElvenArcher) && hasUnit(player, LumberMill);
            default: return false;
        }
    }
}
