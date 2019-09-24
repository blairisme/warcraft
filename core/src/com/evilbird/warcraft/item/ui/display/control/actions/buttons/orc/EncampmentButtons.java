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
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.BasicButtonController;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainGruntButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainTrollAxethrowerButton;
import static com.evilbird.warcraft.item.unit.UnitType.Grunt;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;
import static com.evilbird.warcraft.item.unit.UnitType.TrollLumberMill;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when an Orc Encampment (Barracks) is selected.
 *
 * @author Blair Butterworth
 */
public class EncampmentButtons extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (player.getLevel()) {
            case 1:
            case 2: return singletonList(TrainGruntButton);
            case 3: return asList(TrainGruntButton, TrainTrollAxethrowerButton);
            default: return Collections.emptyList();
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case TrainGruntButton: return hasRequirements(player, Grunt);
            case TrainTrollAxethrowerButton: return hasRequirements(player, TrollAxethrower, TrollLumberMill);
            default: return false;
        }
    }

    private boolean hasRequirements(Player player, UnitType type) {
        return hasResources(player, type);
    }

    private boolean hasRequirements(Player player, UnitType type, UnitType prerequisite) {
        return hasResources(player, type) && hasUnit(player, prerequisite);
    }
}
