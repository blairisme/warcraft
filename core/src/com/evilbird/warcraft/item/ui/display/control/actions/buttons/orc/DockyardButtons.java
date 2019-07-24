/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.ButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainTrollDestroyerButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainTrollTankerButton;
import static com.evilbird.warcraft.item.unit.UnitType.TrollDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.TrollTanker;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when an Orc Dockyard is selected.
 *
 * @author Blair Butterworth
 */
public class DockyardButtons implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (player.getLevel()) {
            case 3: return asList(TrainTrollTankerButton, TrainTrollDestroyerButton);
            default: return Collections.emptyList();
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case TrainTrollTankerButton: return hasResources(player, TrollTanker);
            case TrainTrollDestroyerButton: return hasResources(player, TrollDestroyer);
            default: return false;
        }
    }
}
