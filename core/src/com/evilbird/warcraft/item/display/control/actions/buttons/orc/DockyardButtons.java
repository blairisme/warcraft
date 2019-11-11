/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.orc;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.TrollDestroyerButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.TrollTankerButton;
import static com.evilbird.warcraft.item.unit.UnitType.TrollDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.TrollTanker;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when an Orc Dockyard is selected.
 *
 * @author Blair Butterworth
 */
public class DockyardButtons extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (player.getLevel()) {
            case 3: return asList(TrollTankerButton, TrollDestroyerButton);
            default: return Collections.emptyList();
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case TrollTankerButton: return hasResources(player, TrollTanker);
            case TrollDestroyerButton: return hasResources(player, TrollDestroyer);
            default: return false;
        }
    }
}
