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
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;

import java.util.List;

import static com.evilbird.warcraft.item.unit.UnitType.Mage;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Mage Tower is selected.
 *
 * @author Blair Butterworth
 */
public class MageTowerButtons extends BasicButtonController
{
    private static final List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> BUTTONS = singletonList(com.evilbird.warcraft.item.display.control.actions.ActionButtonType.MageButton);

    @Override
    public List<com.evilbird.warcraft.item.display.control.actions.ActionButtonType> getButtons(Item item) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(com.evilbird.warcraft.item.display.control.actions.ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        return hasResources(player, Mage);
    }
}
