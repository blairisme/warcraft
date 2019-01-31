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

import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.*;

/**
 * Instances of this class define which actions buttons are associated with
 * which units.
 *
 * @author Blair Butterworth
 */
//TODO: cache associations
public class ActionButtonAssociations
{
    public static List<ActionButtonType> getActionButtons(Item item) {
        if (item.getType() == UnitType.Peasant) {
            return getGathererButtons();
        }
        if (item.getType() == UnitType.Footman) {
            return getCombatantButtons();
        }
        if (item.getType() == UnitType.Barracks) {
            return getBarracksButtons();
        }
        if (item.getType() == UnitType.TownHall) {
            return getTownHallButtons();
        }
        return Collections.emptyList();
    }

    public static List<ActionButtonType> getSimpleBuildingButtons() {
        return Arrays.asList(BuildFarmButton, BuildBarracksButton, BuildTownHallButton, BuildCancelButton);
    }

    public static List<ActionButtonType> getAdvancedBuildingButtons() {
        return Arrays.asList(BuildStablesButton, BuildCancelButton);
    }

    private static List<ActionButtonType> getBarracksButtons() {
        return Arrays.asList(TrainFootmanButton);
    }

    private static List<ActionButtonType> getTownHallButtons() {
        return Arrays.asList(TrainPeasantButton);
    }

    private static List<ActionButtonType> getCombatantButtons() {
        return Arrays.asList(MoveButton, StopButton, AttackButton, PatrolButton, DefendButton);
    }

    private static List<ActionButtonType> getGathererButtons() {
        return Arrays.asList(MoveButton, StopButton, AttackButton, RepairButton, GatherButton, BuildSimpleButton, BuildAdvancedButton);
    }
}
