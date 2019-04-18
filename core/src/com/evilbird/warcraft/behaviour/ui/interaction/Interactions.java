/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

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
import static com.evilbird.warcraft.action.train.TrainActions.*;
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
public class Interactions
{
    private InteractionContainer interactions;

    @Inject
    public Interactions(InteractionContainer interactions) {
        this.interactions = interactions;
        addAttackInteractions();
        addMenuInteractions();
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
        interactions.addAction(AttackMelee).whenTarget(Grunt).whenSelected(Footman).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(AttackMelee).whenTarget(Grunt).whenSelected(Peasant).appliedTo(InteractionApplicability.Selected);

        interactions.addAction(ConfirmTarget).whenTarget(Grunt).whenSelected(Footman).appliedTo(InteractionApplicability.Selected).assignedTo(InteractionAssignment.Parent).appliedAs(InteractionDisplacement.Addition);

        interactions.addAction(AttackCancel).whenTarget(CancelButton).whenSelected(Footman).withAction(AttackMelee).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(AttackCancel).whenTarget(CancelButton).whenSelected(Peasant).withAction(AttackMelee).appliedTo(InteractionApplicability.Selected);
    }

    private void addPlaceholderInteractions() {
        interactions.addAction(ConstructBarracks).whenTarget(BarracksPlaceholder).whenSelected(Peasant).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(ConstructFarm).whenTarget(FarmPlaceholder).whenSelected(Peasant).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(ConstructTownHall).whenTarget(TownHallPlaceholder).whenSelected(Peasant).appliedTo(InteractionApplicability.Selected);

        interactions.addAction(PlaceholderMove).forInput(Drag).whenTarget(BarracksPlaceholder).appliedTo(InteractionApplicability.Target);
        interactions.addAction(PlaceholderMove).forInput(Drag).whenTarget(FarmPlaceholder).appliedTo(InteractionApplicability.Target);
        interactions.addAction(PlaceholderMove).forInput(Drag).whenTarget(TownHallPlaceholder).appliedTo(InteractionApplicability.Target);
    }

    private void addCameraInteractions() {
        interactions.addAction(Pan).forInput(Drag).whenTarget(Camera).appliedTo(InteractionApplicability.Target);
        interactions.addAction(Zoom).forInput(UserInputType.Zoom).whenTarget(Camera).appliedTo(InteractionApplicability.Target);
    }

    private void addGatherInteractions() {
        interactions.addAction(GatherGold).whenTarget(GoldMine).whenSelected(Peasant).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(GatherWood).whenTarget(Tree).whenSelected(Peasant).appliedTo(InteractionApplicability.Selected);
    }

    private void addCancelInteractions() {
        interactions.addAction(ConstructBarracksCancel).whenTarget(CancelButton).whenSelected(Barracks).withAction(ConstructBarracks).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(ConstructFarmCancel).whenTarget(CancelButton).whenSelected(Farm).withAction(ConstructFarm).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(ConstructTownhallCancel).whenTarget(CancelButton).whenSelected(TownHall).withAction(ConstructTownHall).appliedTo(InteractionApplicability.Selected);

        interactions.addAction(PlaceholderCancel).whenTarget(CancelButton).whenSelected(BarracksPlaceholder).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(PlaceholderCancel).whenTarget(CancelButton).whenSelected(FarmPlaceholder).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(PlaceholderCancel).whenTarget(CancelButton).whenSelected(TownHallPlaceholder).appliedTo(InteractionApplicability.Selected);

        interactions.addAction(GatherCancel).whenTarget(CancelButton).whenSelected(Peasant).withAction(GatherGold).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(GatherCancel).whenTarget(CancelButton).whenSelected(Peasant).withAction(GatherWood).appliedTo(InteractionApplicability.Selected);

        interactions.addAction(TrainFootmanCancel).whenTarget(CancelButton).whenSelected(Barracks).withAction(TrainFootman).appliedTo(InteractionApplicability.Selected);
    }

    private void addMenuInteractions() {
        interactions.addAction(ActionsMenu).whenTarget(BuildCancelButton).appliedTo(InteractionApplicability.Target);
        interactions.addAction(BuildSimpleMenu).whenTarget(BuildSimpleButton).appliedTo(InteractionApplicability.Target);
        interactions.addAction(BuildAdvancedMenu).whenTarget(BuildAdvancedButton).appliedTo(InteractionApplicability.Target);
        interactions.addAction(IngameMenu).whenTarget(MenuPane).appliedTo(InteractionApplicability.Target);
    }

    public void addConstructInteractions() {
        interactions.addAction(AddBarracksPlaceholder).whenTarget(BuildBarracksButton).whenSelected(Peasant).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(AddFarmPlaceholder).whenTarget(BuildFarmButton).whenSelected(Peasant).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(AddTownHallPlaceholder).whenTarget(BuildTownHallButton).whenSelected(Peasant).appliedTo(InteractionApplicability.Selected);
    }

    private void addTrainInteractions() {
        interactions.addAction(TrainFootman).whenTarget(TrainFootmanButton).whenSelected(Barracks).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(TrainPeasant).whenTarget(TrainPeasantButton).whenSelected(TownHall).appliedTo(InteractionApplicability.Selected);
    }

    private void addMoveInteractions() {
        interactions.addAction(MoveToLocation).whenTarget(Map).whenSelected(Footman).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(MoveToLocation).whenTarget(Map).whenSelected(Peasant).appliedTo(InteractionApplicability.Selected);
//        interactions.addAction(MoveToLocation).whenTarget(Terrain).whenSelected(Grunt).appliedTo(Selected);

        interactions.addAction(ConfirmLocation).whenTarget(Map).whenSelected(Footman).appliedTo(InteractionApplicability.Selected).assignedTo(InteractionAssignment.Parent).appliedAs(InteractionDisplacement.Addition);
        interactions.addAction(ConfirmLocation).whenTarget(Map).whenSelected(Peasant).appliedTo(InteractionApplicability.Selected).assignedTo(InteractionAssignment.Parent).appliedAs(InteractionDisplacement.Addition);
//        interactions.addAction(ConfirmLocation).whenTarget(Terrain).whenSelected(Grunt).appliedTo(Selected).assignedTo(Parent);

        interactions.addAction(MoveCancel).whenTarget(CancelButton).whenSelected(Footman).withAction(MoveToLocation).appliedTo(InteractionApplicability.Selected);
        interactions.addAction(MoveCancel).whenTarget(CancelButton).whenSelected(Peasant).withAction(MoveToLocation).appliedTo(InteractionApplicability.Selected);
    }

    private void addSelectionInteractions() {
        interactions.addAction(SelectToggle).whenTarget(Footman).appliedTo(InteractionApplicability.Target).appliedAs(InteractionDisplacement.Addition);
        interactions.addAction(SelectToggle).whenTarget(Peasant).appliedTo(InteractionApplicability.Target).appliedAs(InteractionDisplacement.Addition);
        interactions.addAction(SelectToggle).whenTarget(GoldMine).appliedTo(InteractionApplicability.Target).appliedAs(InteractionDisplacement.Addition);
        interactions.addAction(SelectToggle).whenTarget(UnitType.TownHall).appliedTo(InteractionApplicability.Target).appliedAs(InteractionDisplacement.Addition);
        interactions.addAction(SelectToggle).whenTarget(UnitType.Barracks).appliedTo(InteractionApplicability.Target).appliedAs(InteractionDisplacement.Addition);
        interactions.addAction(SelectToggle).whenTarget(UnitType.Farm).appliedTo(InteractionApplicability.Target).appliedAs(InteractionDisplacement.Addition);
    }
}
