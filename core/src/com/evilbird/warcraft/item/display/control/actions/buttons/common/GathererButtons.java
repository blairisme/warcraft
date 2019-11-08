/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.common;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.display.control.actions.buttons.ButtonController;

import java.util.List;

import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.BuildAdvancedButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.BuildSimpleButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.GatherButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.RepairButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.StopButton;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a gatherer is selected.
 *
 * @author Blair Butterworth
 */
public class GathererButtons implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        return asList(MoveButton, StopButton, AttackButton, RepairButton, GatherButton,
            BuildSimpleButton, BuildAdvancedButton);
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        if (button == StopButton || button == BuildSimpleButton) {
            return true;
        }
        if (button == BuildAdvancedButton) {
            Player player = UnitOperations.getPlayer(item);
            return player.getLevel() > 2;
        }
        return false;
    }
}
