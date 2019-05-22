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
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.UnitCosts;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.List;
import java.util.Map;

import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildAdvancedButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildBarracksButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildFarmButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildSimpleButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildStablesButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildTownHallButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.DefendButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.GatherButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.PatrolButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.RepairButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.StopButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.TrainFootmanButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.TrainPeasantButton;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;
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
    private static List<ActionButtonType> unsupported = asList(
        MoveButton, AttackButton, PatrolButton, DefendButton, RepairButton,
        GatherButton, BuildStablesButton);

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

    private static Map<ActionButtonType, UnitType> products = Maps.of(
            TrainFootmanButton, Footman,
            TrainPeasantButton, Peasant,
            BuildFarmButton,    Farm,
            BuildBarracksButton, Barracks,
            BuildTownHallButton, TownHall);

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

    public static boolean isEnabled(ActionButtonType type, Map<ResourceType, Float> resources) {
        return isSupported(type) && hasResources(type, resources);
    }

    private static boolean isSupported(ActionButtonType type) {
        return !unsupported.contains(type);
    }

    private static boolean hasResources(ActionButtonType button, Map<ResourceType, Float> resources) {
        if (products.containsKey(button)) {
            UnitType type = products.get(button);
            for (ResourceQuantity cost: UnitCosts.costOf(type)) {
                if (getResource(resources, cost.getResource()) < cost.getValue()) {
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
