/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.actions;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.resource.ResourceType;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.evilbird.warcraft.item.common.resource.ResourceType.Gold;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.*;
import static com.evilbird.warcraft.item.unit.UnitType.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * Instances of this class define which actions buttons are associated with
 * which units.
 *
 * @author Blair Butterworth
 */
public class ActionButtonDetails
{
    private static List<ActionButtonType> simpleBuildings = asList(
        BuildFarmButton, BuildBarracksButton, BuildTownHallButton, BuildCancelButton);

    private static List<ActionButtonType> advancedBuildings = asList(
        BuildStablesButton, BuildCancelButton);

    private static Map<Identifier, List<ActionButtonType>> types = Maps.of(
        Footman, asList(MoveButton, StopButton, AttackButton, PatrolButton, DefendButton),
        Peasant, asList(MoveButton, StopButton, AttackButton, RepairButton, GatherButton,
                        BuildSimpleButton, BuildAdvancedButton),
        Barracks, singletonList(TrainFootmanButton),
        TownHall, singletonList(TrainPeasantButton));

    private static Map<ActionButtonType, Map<ResourceType, Float>> costs = Maps.of(
        TrainFootmanButton, Maps.of(Gold, 250f),
        TrainPeasantButton, Maps.of(Gold, 100f),
        BuildFarmButton,    Maps.of(Gold, 100f),
        BuildBarracksButton, Maps.of(Gold, 100f),
        BuildTownHallButton, Maps.of(Gold, 100f));

    public static List<ActionButtonType> getActionButtons(Item item) {
        if (types.containsKey(item.getType())){
            return types.get(item.getType());
        }
        return emptyList();
    }

    public static List<ActionButtonType> getSimpleBuildingButtons() {
        return simpleBuildings;
    }

    public static List<ActionButtonType> getAdvancedBuildingButtons() {
        return advancedBuildings;
    }

    public static boolean hasResources(ActionButtonType type, Map<ResourceType, Float> resources) {
        if (costs.containsKey(type)) {
            for (Entry<ResourceType, Float> cost: costs.get(type).entrySet()) {
                if (getResource(resources, cost.getKey()) < cost.getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static float getResource(Map<ResourceType, Float> resources, ResourceType type) {
        if (resources.containsKey(type)) {
             return resources.get(type);
        }
        return 0;
    }

    private ActionButtonDetails() {
    }
}
