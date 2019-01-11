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
import com.evilbird.warcraft.action.identifier.*;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.site.BuildSiteType;

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
        addSelectionInteraction(UserInputType.Action, UnitType.Grunt, UnitType.Footman, CommonActionType.Attack);
    }

    private void addBuildingSiteInteractions() {
        addSelectionInteraction(UserInputType.Action, BuildSiteType.Barracks, UnitType.Peasant, BuildActionType.BuildBarracks);
        addSelectionInteraction(UserInputType.Action, BuildSiteType.Farm, UnitType.Peasant, BuildActionType.BuildFarm);
        addSelectionInteraction(UserInputType.Action, BuildSiteType.TownHall, UnitType.Peasant, BuildActionType.BuildTownHall);

        addTargetInteraction(UserInputType.Drag, BuildSiteType.Barracks, CommonActionType.Reposition);
        addTargetInteraction(UserInputType.Drag, BuildSiteType.Farm, CommonActionType.Reposition);
        addTargetInteraction(UserInputType.Drag, BuildSiteType.TownHall, CommonActionType.Reposition);
    }

    private void addCameraInteractions() {
        addTargetInteraction(UserInputType.Zoom, DataType.Camera, CameraActionType.Zoom);
        addTargetInteraction(UserInputType.Drag, DataType.Camera, CameraActionType.Pan);
    }

    private void addGatherInteractions() {
        addSelectionInteraction(UserInputType.Action, UnitType.GoldMine, UnitType.Peasant, GatherActionType.GatherGold);
        addSelectionInteraction(UserInputType.Action, UnitType.Tree, UnitType.Peasant, GatherActionType.GatherWood);
    }

    private void addHudInteractions() {
        addSelectionInteraction(UserInputType.Action, CommonActionType.Stop, UnitType.Footman, CommonActionType.Stop);
        addSelectionInteraction(UserInputType.Action, CommonActionType.Stop, UnitType.Peasant, CommonActionType.Stop);

        addSelectionInteraction(UserInputType.Action, BuildActionType.BuildBarracks, UnitType.Peasant, SiteActionType.BuildBarracksSite);
        addSelectionInteraction(UserInputType.Action, BuildActionType.BuildFarm, UnitType.Peasant, SiteActionType.BuildFarmSite);
        addSelectionInteraction(UserInputType.Action, BuildActionType.BuildTownHall, UnitType.Peasant, SiteActionType.BuildTownHallSite);

        addSelectionInteraction(UserInputType.Action, CommonActionType.Cancel, UnitType.TownHall, CommonActionType.Cancel);
        addSelectionInteraction(UserInputType.Action, CommonActionType.Cancel, UnitType.Barracks, CommonActionType.Cancel);
//        addSelectionInteraction(UserInputType.Action, CommonActionType.Cancel, BuildSiteType.Barracks, CommonActionType.Cancel);
//        addSelectionInteraction(UserInputType.Action, CommonActionType.Cancel, BuildSiteType.Farm, CommonActionType.Cancel);
//        addSelectionInteraction(UserInputType.Action, CommonActionType.Cancel, BuildSiteType.TownHall, CommonActionType.Cancel);

        addSelectionInteraction(UserInputType.Action, TrainActionType.TrainPeasant, UnitType.TownHall, TrainActionType.TrainPeasant);
        addSelectionInteraction(UserInputType.Action, TrainActionType.TrainFootman, UnitType.Barracks, TrainActionType.TrainFootman);
    }

    private void addMoveInteractions() {
        addSelectionInteraction(UserInputType.Action, LayerType.Map, UnitType.Footman, CommonActionType.Move);
        addSelectionInteraction(UserInputType.Action, LayerType.Map, UnitType.Peasant, CommonActionType.Move);
        addSelectionInteraction(UserInputType.Action, LayerType.Map, UnitType.Grunt, CommonActionType.Move);
    }

    private void addSelectionInteractions() {
        addTargetInteraction(UserInputType.Action, UnitType.Footman, CommonActionType.Select);
        addTargetInteraction(UserInputType.Action, UnitType.Peasant, CommonActionType.Select);
        addTargetInteraction(UserInputType.Action, UnitType.Grunt, CommonActionType.Select);
        addTargetInteraction(UserInputType.Action, UnitType.GoldMine, CommonActionType.Select);
        addTargetInteraction(UserInputType.Action, UnitType.TownHall, CommonActionType.Select);
        addTargetInteraction(UserInputType.Action, UnitType.Barracks, CommonActionType.Select);
        addTargetInteraction(UserInputType.Action, UnitType.Farm, CommonActionType.Select);
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
