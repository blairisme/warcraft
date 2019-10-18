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

import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUnits;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildCastleButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainPeasantButton;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Blacksmith;
import static com.evilbird.warcraft.item.unit.UnitType.Castle;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static com.evilbird.warcraft.item.unit.UnitType.Stables;
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
        singletonList(TrainPeasantButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
        asList(TrainPeasantButton, BuildCastleButton);

    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        return player.getLevel() <= 10 ? BASIC_BUTTONS : ADVANCED_BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);

        if (button == TrainPeasantButton) {
            return hasResources(player, Peasant);
        }
        if (button == BuildCastleButton) {
            return hasResources(player, Castle) && hasUnits(player, Barracks, Blacksmith, Stables);
        }
        return false;
    }
}
