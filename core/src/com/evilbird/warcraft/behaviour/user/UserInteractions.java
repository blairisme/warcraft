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
import java.util.Collection;

import static com.evilbird.engine.device.UserInputType.Drag;
import static com.evilbird.warcraft.action.attack.AttackActions.AttackCancel;
import static com.evilbird.warcraft.action.attack.AttackActions.AttackMelee;
import static com.evilbird.warcraft.action.camera.CameraActions.Pan;
import static com.evilbird.warcraft.action.camera.CameraActions.Zoom;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmLocation;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmTarget;
import static com.evilbird.warcraft.action.construct.ConstructActions.*;
import static com.evilbird.warcraft.action.gather.GatherActions.*;
import static com.evilbird.warcraft.action.menu.MenuActions.*;
import static com.evilbird.warcraft.action.move.MoveActions.MoveCancel;
import static com.evilbird.warcraft.action.move.MoveActions.MoveToLocation;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.*;
import static com.evilbird.warcraft.action.select.SelectActions.SelectToggle;
import static com.evilbird.warcraft.action.train.TrainActions.TrainFootman;
import static com.evilbird.warcraft.action.train.TrainActions.TrainPeasant;
import static com.evilbird.warcraft.behaviour.user.InteractionApplicability.Selected;
import static com.evilbird.warcraft.behaviour.user.InteractionApplicability.Target;
import static com.evilbird.warcraft.behaviour.user.InteractionAssignment.Parent;
import static com.evilbird.warcraft.behaviour.user.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.item.data.DataType.Camera;
import static com.evilbird.warcraft.item.hud.HudControl.MenuPane;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.*;
import static com.evilbird.warcraft.item.layer.LayerType.Map;
import static com.evilbird.warcraft.item.layer.LayerType.Tree;
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
        addBuildMenuInteractions();
        addCameraInteractions();
        addCancelInteractions();
        addConstructInteractions();
        addGatherInteractions();
        addTrainInteractions();
        addMoveInteractions();
        addPlaceholderInteractions();
        addSelectionInteractions();
    }

    public Collection<Interaction> getInteractions(UserInput input, Item item, Item selected) {
        return interactions.getInteractions(input, item, selected);
    }

    private void addAttackInteractions() {
        interactions.addAction(AttackMelee).whenTarget(Grunt).whenSelected(Footman).appliedTo(Selected);
        interactions.addAction(AttackMelee).whenTarget(Grunt).whenSelected(Peasant).appliedTo(Selected);

        interactions.addAction(ConfirmTarget).whenTarget(Grunt).whenSelected(Footman).appliedTo(Selected).assignedTo(Parent).appliedAs(Addition);

        interactions.addAction(AttackCancel).whenTarget(CancelButton).whenSelected(Footman).withAction(AttackMelee).appliedTo(Selected);
        interactions.addAction(AttackCancel).whenTarget(CancelButton).whenSelected(Peasant).withAction(AttackMelee).appliedTo(Selected);
    }

    private void addPlaceholderInteractions() {
        interactions.addAction(ConstructBarracks).whenTarget(BarracksPlaceholder).whenSelected(Peasant).appliedTo(Selected);
        interactions.addAction(ConstructFarm).whenTarget(FarmPlaceholder).whenSelected(Peasant).appliedTo(Selected);
        interactions.addAction(ConstructTownHall).whenTarget(TownHallPlaceholder).whenSelected(Peasant).appliedTo(Selected);

        interactions.addAction(PlaceholderMove).forInput(Drag).whenTarget(BarracksPlaceholder).appliedTo(Target);
        interactions.addAction(PlaceholderMove).forInput(Drag).whenTarget(FarmPlaceholder).appliedTo(Target);
        interactions.addAction(PlaceholderMove).forInput(Drag).whenTarget(TownHallPlaceholder).appliedTo(Target);
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
        interactions.addAction(ConstructBarracksCancel).whenTarget(CancelButton).whenSelected(Barracks).withAction(ConstructBarracks).appliedTo(Selected);
        interactions.addAction(ConstructFarmCancel).whenTarget(CancelButton).whenSelected(Farm).withAction(ConstructFarm).appliedTo(Selected);
        interactions.addAction(ConstructTownhallCancel).whenTarget(CancelButton).whenSelected(TownHall).withAction(ConstructTownHall).appliedTo(Selected);

        interactions.addAction(PlaceholderCancel).whenTarget(CancelButton).whenSelected(BarracksPlaceholder).appliedTo(Selected);
        interactions.addAction(PlaceholderCancel).whenTarget(CancelButton).whenSelected(FarmPlaceholder).appliedTo(Selected);
        interactions.addAction(PlaceholderCancel).whenTarget(CancelButton).whenSelected(TownHallPlaceholder).appliedTo(Selected);

        interactions.addAction(GatherCancel).whenTarget(CancelButton).whenSelected(Peasant).withAction(GatherGold).appliedTo(Selected);
        interactions.addAction(GatherCancel).whenTarget(CancelButton).whenSelected(Peasant).withAction(GatherWood).appliedTo(Selected);
    }

    private void addBuildMenuInteractions() {
        interactions.addAction(ActionsMenu).whenTarget(BuildCancelButton).appliedTo(Target);
        interactions.addAction(BuildSimpleMenu).whenTarget(BuildSimpleButton).appliedTo(Target);
        interactions.addAction(BuildAdvancedMenu).whenTarget(BuildAdvancedButton).appliedTo(Target);


        //TODO: IngameMenu temporary
        interactions.addAction(IngameMenu).whenTarget(MenuPane).appliedTo(Target);
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
        interactions.addAction(MoveToLocation).whenTarget(Map).whenSelected(Footman).appliedTo(Selected);
        interactions.addAction(MoveToLocation).whenTarget(Map).whenSelected(Peasant).appliedTo(Selected);
//        interactions.addAction(MoveToLocation).whenTarget(Terrain).whenSelected(Grunt).appliedTo(Selected);

        //TODO: Shouldn't assign to parent (player) - should player show visuals or play sounds?
        interactions.addAction(ConfirmLocation).whenTarget(Map).whenSelected(Footman).appliedTo(Selected).assignedTo(Parent).appliedAs(Addition);
        interactions.addAction(ConfirmLocation).whenTarget(Map).whenSelected(Peasant).appliedTo(Selected).assignedTo(Parent).appliedAs(Addition);
//        interactions.addAction(ConfirmLocation).whenTarget(Terrain).whenSelected(Grunt).appliedTo(Selected).assignedTo(Parent);

        interactions.addAction(MoveCancel).whenTarget(CancelButton).whenSelected(Footman).withAction(MoveToLocation).appliedTo(Selected);
        interactions.addAction(MoveCancel).whenTarget(CancelButton).whenSelected(Peasant).withAction(MoveToLocation).appliedTo(Selected);
    }

    private void addSelectionInteractions() {
        interactions.addAction(SelectToggle).whenTarget(Footman).appliedTo(Target);
        interactions.addAction(SelectToggle).whenTarget(Peasant).appliedTo(Target);
        interactions.addAction(SelectToggle).whenTarget(GoldMine).appliedTo(Target);
        interactions.addAction(SelectToggle).whenTarget(UnitType.TownHall).appliedTo(Target);
        interactions.addAction(SelectToggle).whenTarget(UnitType.Barracks).appliedTo(Target);
        interactions.addAction(SelectToggle).whenTarget(UnitType.Farm).appliedTo(Target);
    }
}
