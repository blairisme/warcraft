/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.user;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.type.*;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.site.BuildSiteType;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Instances of this class define the different ways the user can interact with
 * the game and the actions that result from these interactions.
 *
 * @author Blair Butterworth
 */
public class UserInteractions
{
    private Collection<Interaction> interactions;
    private Provider<TargetInteraction> targetFactory;
    private Provider<SelectionInteraction> selectionFactory;

    @Inject
    public UserInteractions(Provider<SelectionInteraction> selectionFactory, Provider<TargetInteraction> targetFactory) {
        this.interactions = new ArrayList<>();
        this.selectionFactory = selectionFactory;
        this.targetFactory = targetFactory;
        addInteractions();
    }

    public Interaction getInteraction(UserInput input, Item target, Item worldSelection, Item hudSelection) {
        for (Interaction interaction: interactions) {
            if (interaction.applies(input, target, worldSelection, hudSelection)) {
                return interaction;
            }
        }
        return null;
    }

    private void addInteractions() {
        addAttackInteractions();
        addBuildingSiteInteractions();
        addCameraInteractions();
        addGatherInteractions();
        addHudInteractions();
        addMoveInteractions();
        addSelectionInteractions();
    }

    private void addAttackInteractions() {
        addSelectionInteraction(UserInputType.Action, UnitType.Grunt, UnitType.Footman, CommonAction.Attack);
    }

    private void addBuildingSiteInteractions() {
        addSelectionInteraction(UserInputType.Action, BuildSiteType.Barracks, UnitType.Peasant, BuildAction.BuildBarracks);
        addSelectionInteraction(UserInputType.Action, BuildSiteType.Farm, UnitType.Peasant, BuildAction.BuildFarm);
        addSelectionInteraction(UserInputType.Action, BuildSiteType.TownHall, UnitType.Peasant, BuildAction.BuildTownHall);

        addTargetInteraction(UserInputType.Drag, BuildSiteType.Barracks, CommonAction.Reposition);
        addTargetInteraction(UserInputType.Drag, BuildSiteType.Farm, CommonAction.Reposition);
        addTargetInteraction(UserInputType.Drag, BuildSiteType.TownHall, CommonAction.Reposition);
    }

    private void addCameraInteractions() {
        addTargetInteraction(UserInputType.Zoom, DataType.Camera, CameraAction.Zoom);
        addTargetInteraction(UserInputType.Drag, DataType.Camera, CameraAction.Pan);
    }

    private void addGatherInteractions() {
        addSelectionInteraction(UserInputType.Action, UnitType.GoldMine, UnitType.Peasant,GatherAction.GatherGold);
        addSelectionInteraction(UserInputType.Action, UnitType.Tree, UnitType.Peasant, GatherAction.GatherWood);
    }

    private void addHudInteractions() {
        addSelectionInteraction(UserInputType.Action, CommonAction.Stop, UnitType.Footman, CommonAction.Stop);
        addSelectionInteraction(UserInputType.Action, CommonAction.Stop, UnitType.Peasant, CommonAction.Stop);

        addSelectionInteraction(UserInputType.Action, BuildAction.BuildBarracks, UnitType.Peasant, BuildSiteAction.BuildBarracksSite);
        addSelectionInteraction(UserInputType.Action, BuildAction.BuildFarm, UnitType.Peasant, BuildSiteAction.BuildFarmSite);
        addSelectionInteraction(UserInputType.Action, BuildAction.BuildTownHall, UnitType.Peasant, BuildSiteAction.BuildTownHallSite);

        addSelectionInteraction(UserInputType.Action, CommonAction.Cancel, UnitType.TownHall, CommonAction.Cancel);
        addSelectionInteraction(UserInputType.Action, CommonAction.Cancel, UnitType.Barracks, CommonAction.Cancel);
//        addSelectionInteraction(UserInputType.Action, CommonAction.Cancel, BuildSiteType.Barracks, CommonAction.Cancel);
//        addSelectionInteraction(UserInputType.Action, CommonAction.Cancel, BuildSiteType.Farm, CommonAction.Cancel);
//        addSelectionInteraction(UserInputType.Action, CommonAction.Cancel, BuildSiteType.TownHall, CommonAction.Cancel);

        addSelectionInteraction(UserInputType.Action, TrainAction.TrainPeasant, UnitType.TownHall, TrainAction.TrainPeasant);
        addSelectionInteraction(UserInputType.Action, TrainAction.TrainFootman, UnitType.Barracks, TrainAction.TrainFootman);
    }

    private void addMoveInteractions() {
        addSelectionInteraction(UserInputType.Action, LayerType.Map, UnitType.Footman, CommonAction.Move);
        addSelectionInteraction(UserInputType.Action, LayerType.Map, UnitType.Peasant, CommonAction.Move);
        addSelectionInteraction(UserInputType.Action, LayerType.Map, UnitType.Grunt, CommonAction.Move);
    }

    private void addSelectionInteractions() {
        addTargetInteraction(UserInputType.Action, UnitType.Footman, CommonAction.Select);
        addTargetInteraction(UserInputType.Action, UnitType.Peasant, CommonAction.Select);
        addTargetInteraction(UserInputType.Action, UnitType.Grunt, CommonAction.Select);
        addTargetInteraction(UserInputType.Action, UnitType.GoldMine, CommonAction.Select);
        addTargetInteraction(UserInputType.Action, UnitType.TownHall, CommonAction.Select);
        addTargetInteraction(UserInputType.Action, UnitType.Barracks, CommonAction.Select);
        addTargetInteraction(UserInputType.Action, UnitType.Farm, CommonAction.Select);
    }

    private void addSelectionInteraction(
        UserInputType inputType,
        Identifier targetType,
        Identifier selectedType,
        ActionIdentifier actionType)
    {
        SelectionInteraction result = selectionFactory.get();
        result.setActionType(actionType);
        result.setInputType(inputType);
        result.setTargetType(targetType);
        result.setSelectedType(selectedType);
        interactions.add(result);
    }

    private void addTargetInteraction(
        UserInputType inputType,
        Identifier targetType,
        ActionIdentifier actionType)
    {
        TargetInteraction result = targetFactory.get();
        result.setActionType(actionType);
        result.setInputType(inputType);
        result.setTargetType(targetType);
        interactions.add(result);
    }
}
