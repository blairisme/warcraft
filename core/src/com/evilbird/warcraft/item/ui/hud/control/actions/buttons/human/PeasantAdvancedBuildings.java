/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.hud.control.actions.buttons.ButtonController;

import java.util.List;

import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildDockButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildStablesButton;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Peasant is selected and the user navigates
 * to the advanced building menu.
 *
 * @author Blair Butterworth
 */
public class PeasantAdvancedBuildings implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (player.getLevel()) {
            case 1: return asList(
                    BuildStablesButton, BuildCancelButton);
            default: return asList(
                    BuildStablesButton, BuildDockButton, BuildCancelButton);
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        return button == BuildCancelButton;
    }
}
