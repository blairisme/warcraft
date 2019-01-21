/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.actions;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActionButtonAssociations
{
    public static List<ActionButtonType> getActionButtons(Item item) {
        if (item.getType() == UnitType.Peasant) {
            return getGathererButtons();
        }
        if (item.getType() == UnitType.Barracks) {
            return getBarracksButtons();
        }
        if (item.getType() == UnitType.TownHall) {
            return getTownhallButtons();
        }
        return Collections.emptyList();
    }

    private static List<ActionButtonType> getBarracksButtons() {
        return Arrays.asList(ActionButtonType.TrainFootmanButton);
    }

    private static List<ActionButtonType> getTownhallButtons() {
        return Arrays.asList(ActionButtonType.TrainPeasantButton);
    }

    private static List<ActionButtonType> getGathererButtons() {
        return Arrays.asList(ActionButtonType.BuildBarracksButton);
    }

}
