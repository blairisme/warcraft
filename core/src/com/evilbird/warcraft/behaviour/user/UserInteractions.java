/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.user;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;

import javax.inject.Inject;

import static com.evilbird.engine.device.UserInputType.Drag;
import static com.evilbird.warcraft.action.identifier.GeneralActions.*;
import static com.evilbird.warcraft.action.identifier.CameraActions.*;
import static com.evilbird.warcraft.action.identifier.CancelActions.*;
import static com.evilbird.warcraft.action.identifier.GatherActions.*;
import static com.evilbird.warcraft.action.identifier.PlaceholderActions.AddBarracksPlaceholder;
import static com.evilbird.warcraft.action.identifier.PlaceholderActions.AddFarmPlaceholder;
import static com.evilbird.warcraft.action.identifier.PlaceholderActions.AddTownHallPlaceholder;
import static com.evilbird.warcraft.action.identifier.TrainActions.*;
import static com.evilbird.warcraft.action.identifier.ConstructionActions.*;
import static com.evilbird.warcraft.behaviour.user.InteractionApplicability.*;
import static com.evilbird.warcraft.item.data.DataType.Camera;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.*;
import static com.evilbird.warcraft.item.layer.LayerType.*;
import static com.evilbird.warcraft.item.placeholder.PlaceholderType.*;
import static com.evilbird.warcraft.item.unit.UnitType.*;

/**
 * Instances of this class define the different ways the user can interact with
 * the game and the actions that result from these interactions.
 *
 * @author Blair Butterworth
 */
public class UserInteractions
{
    private InteractionContainer interactions;

    @Inject
    public UserInteractions(InteractionContainer interactions) {
        this.interactions = interactions;
        addAttackInteractions();
        addCameraInteractions();
        addCancelInteractions();
        addConstructInteractions();
        addGatherInteractions();
        addTrainInteractions();
        addMoveInteractions();
        addPlaceholderInteractions();
        addSelectionInteractions();
    }

    public Interaction getInteraction(UserInput input, Item item, Item selected) {
        return interactions.getInteraction(input, item, selected);
    }

    private void addAttackInteractions() {
        interactions.addAction(Attack).whenTarget(Grunt).whenSelected(Footman).appliedTo(Selected);
    }

    private void addPlaceholderInteractions() {
        interactions.addAction(ConstructBarracks).whenTarget(BarracksPlaceholder).whenSelected(Peasant).appliedTo(Selected);
        interactions.addAction(ConstructFarm).whenTarget(FarmPlaceholder).whenSelected(Peasant).appliedTo(Selected);
        interactions.addAction(ConstructTownHall).whenTarget(TownHallPlaceholder).whenSelected(Peasant).appliedTo(Selected);

        interactions.addAction(Reposition).forInput(Drag).whenTarget(BarracksPlaceholder).appliedTo(Target);
        interactions.addAction(Reposition).forInput(Drag).whenTarget(FarmPlaceholder).appliedTo(Target);
        interactions.addAction(Reposition).forInput(Drag).whenTarget(TownHallPlaceholder).appliedTo(Target);
    }

    private void addCameraInteractions() {
        interactions.addAction(Pan).forInput(Drag).whenTarget(Camera).appliedTo(Target);
        interactions.addAction(Zoom).forInput(UserInputType.Zoom).whenTarget(Camera).appliedTo(Target);
    }

    private void addGatherInteractions() {
        interactions.addAction(GatherGold).whenTarget(GoldMine).whenSelected(Peasant).appliedTo(Selected);
        interactions.addAction(GatherWood).whenTarget(Tree).whenSelected(Peasant).appliedTo(Selected);
    }

    private void addCancelInteractions() {
        interactions.addAction(CancelAttack).whenTarget(CancelButton).whenSelected(Footman).withAction(Attack).appliedTo(Selected);
        interactions.addAction(CancelAttack).whenTarget(CancelButton).whenSelected(Peasant).withAction(Attack).appliedTo(Selected);

        interactions.addAction(CancelConstruct).whenTarget(CancelButton).whenSelected(Barracks).withAction(ConstructBarracks).appliedTo(Selected);
        interactions.addAction(CancelConstruct).whenTarget(CancelButton).whenSelected(Farm).withAction(ConstructFarm).appliedTo(Selected);
        interactions.addAction(CancelConstruct).whenTarget(CancelButton).whenSelected(TownHall).withAction(ConstructTownHall).appliedTo(Selected);

        interactions.addAction(CancelPlaceholder).whenTarget(CancelButton).whenSelected(BarracksPlaceholder).appliedTo(Selected);
        interactions.addAction(CancelPlaceholder).whenTarget(CancelButton).whenSelected(FarmPlaceholder).appliedTo(Selected);
        interactions.addAction(CancelPlaceholder).whenTarget(CancelButton).whenSelected(TownHallPlaceholder).appliedTo(Selected);

        interactions.addAction(CancelMove).whenTarget(CancelButton).whenSelected(Footman).withAction(Move).appliedTo(Selected);
        interactions.addAction(CancelMove).whenTarget(CancelButton).whenSelected(Peasant).withAction(Move).appliedTo(Selected);

        interactions.addAction(CancelGather).whenTarget(CancelButton).whenSelected(Peasant).withAction(GatherGold).appliedTo(Selected);
        interactions.addAction(CancelGather).whenTarget(CancelButton).whenSelected(Peasant).withAction(GatherWood).appliedTo(Selected);
    }

    public void addConstructInteractions() {
        interactions.addAction(AddBarracksPlaceholder).whenTarget(BuildBarracksButton).whenSelected(Peasant).appliedTo(Selected);
        interactions.addAction(AddFarmPlaceholder).whenTarget(BuildFarmButton).whenSelected(Peasant).appliedTo(Selected);
        interactions.addAction(AddTownHallPlaceholder).whenTarget(BuildTownHallButton).whenSelected(Peasant).appliedTo(Selected);
    }

    private void addTrainInteractions() {
        interactions.addAction(TrainFootman).whenTarget(TrainFootmanButton).whenSelected(Barracks).appliedTo(Selected);
        interactions.addAction(TrainPeasant).whenTarget(TrainPeasantButton).whenSelected(TownHall).appliedTo(Selected);
    }

    private void addMoveInteractions() {
        interactions.addAction(Move).whenTarget(Map).whenSelected(Footman).appliedTo(Selected);
        interactions.addAction(Move).whenTarget(Map).whenSelected(Peasant).appliedTo(Selected);
        interactions.addAction(Move).whenTarget(Map).whenSelected(Grunt).appliedTo(Selected);
    }

    private void addSelectionInteractions() {
        interactions.addAction(Select).whenTarget(Footman).appliedTo(Target);
        interactions.addAction(Select).whenTarget(Peasant).appliedTo(Target);
        interactions.addAction(Select).whenTarget(Grunt).appliedTo(Target);
        interactions.addAction(Select).whenTarget(GoldMine).appliedTo(Target);
        interactions.addAction(Select).whenTarget(UnitType.TownHall).appliedTo(Target);
        interactions.addAction(Select).whenTarget(UnitType.Barracks).appliedTo(Target);
        interactions.addAction(Select).whenTarget(UnitType.Farm).appliedTo(Target);
    }
}
