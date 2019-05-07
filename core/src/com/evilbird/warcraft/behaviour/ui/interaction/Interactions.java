/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.placeholder.PlaceholderType;
import com.evilbird.warcraft.item.unit.UnitType;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.device.UserInputType.Drag;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
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
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Target;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionAssignment.Parent;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.*;
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
    private static final Map<ActionIdentifier, ActionButtonType> ADD_PLACEHOLDER_TARGETS = Maps.of(
            AddBarracksPlaceholder, BuildBarracksButton,
            AddFarmPlaceholder, BuildFarmButton,
            AddTownHallPlaceholder, BuildTownHallButton);

    private static final Map<ActionIdentifier, PlaceholderType> BEGIN_CONSTRUCTION_TARGETS = Maps.of(
            ConstructBarracks, BarracksPlaceholder,
            ConstructFarm, FarmPlaceholder,
            ConstructTownHall, TownHallPlaceholder);

    private static final Map<ActionIdentifier, UnitType> CANCEL_CONSTRUCTION_TARGETS = Maps.of(
            ConstructBarracksCancel, Barracks,
            ConstructFarmCancel, Farm,
            ConstructTownhallCancel, TownHall);

    private InteractionContainer interactions;

    @Inject
    public Interactions(InteractionContainer interactions) {
        this.interactions = interactions;
        addAttackInteractions();
        addMenuInteractions();
        addCameraInteractions();
        constructionInteractions();
        addGatherInteractions();
        addTrainInteractions();
        addMoveInteractions();
        addSelectionInteractions();
    }

    public Collection<Interaction> getInteractions(UserInput input, Item item, Item selected) {
        return interactions.getInteractions(input, item, selected);
    }

    private void addAttackInteractions() {
        interactions.addAction(AttackMelee).whenTarget(Grunt).whenSelected(Footman).appliedTo(Selected);
        interactions.addAction(AttackMelee).whenTarget(Grunt).whenSelected(Peasant).appliedTo(Selected);

        interactions.addAction(ConfirmTarget).whenTarget(Grunt).whenSelected(Footman).appliedTo(Selected).assignedTo(Parent).appliedAs(Addition);
        interactions.addAction(ConfirmTarget).whenTarget(Grunt).whenSelected(Peasant).appliedTo(Selected).assignedTo(Parent).appliedAs(Addition);

        interactions.addAction(AttackCancel).whenTarget(CancelButton).whenSelected(Footman).withAction(AttackMelee).appliedTo(Selected);
        interactions.addAction(AttackCancel).whenTarget(CancelButton).whenSelected(Peasant).withAction(AttackMelee).appliedTo(Selected);
    }

    private void constructionInteractions() {
        addPlaceholder();
        cancelPlaceholder();
        dragPlaceholder();
        beginConstruction();
        cancelConstruction();
    }

    public void addPlaceholder() {
        ADD_PLACEHOLDER_TARGETS.forEach(this::addPlaceholder);
    }

    public void addPlaceholder(ActionIdentifier placeholderType, Identifier buttonType) {
        interactions.addAction(placeholderType)
            .whenTarget(buttonType)
            .whenSelected(Peasant)
            .appliedTo(Selected);
    }

    private void cancelPlaceholder() {
        interactions.addAction(PlaceholderCancel)
            .whenTarget(CancelButton)
            .whenSelected(isGathererConstructing())
            .appliedTo(Selected);
    }

    private void dragPlaceholder() {
        interactions.addAction(PlaceholderMove)
            .forInput(Drag)
            .whenTarget(isPlaceholder())
            .appliedTo(Target);
    }

    private void beginConstruction() {
        BEGIN_CONSTRUCTION_TARGETS.forEach(this::beginConstruction);
    }

    private void beginConstruction(ActionIdentifier construction, PlaceholderType placeholder) {
        interactions.addAction(construction)
            .whenTarget(both(withType(placeholder), isPlaceholderClear()))
            .whenSelected(Peasant)
            .appliedTo(Selected);
    }

    private void cancelConstruction() {
        CANCEL_CONSTRUCTION_TARGETS.forEach(this::cancelConstruction);
    }

    private void cancelConstruction(ActionIdentifier cancel, UnitType building) {
        interactions.addAction(cancel)
            .whenTarget(CancelButton)
            .whenSelected(both(withType(building), isUnderConstruction()))
            .appliedTo(Selected);
    }

    private void addCameraInteractions() {
        interactions.addAction(Pan).forInput(Drag).whenTarget(Camera).appliedTo(Target);
        interactions.addAction(Zoom).forInput(UserInputType.Zoom).whenTarget(Camera).appliedTo(Target);
    }

    private void addGatherInteractions() {
        interactions.addAction(GatherGold).whenTarget(GoldMine).whenSelected(Peasant).appliedTo(Selected);
        interactions.addAction(GatherWood).whenTarget(Tree).whenSelected(Peasant).appliedTo(Selected);

        interactions.addAction(GatherCancel).whenTarget(CancelButton).whenSelected(Peasant).withAction(GatherGold).appliedTo(Selected);
        interactions.addAction(GatherCancel).whenTarget(CancelButton).whenSelected(Peasant).withAction(GatherWood).appliedTo(Selected);
    }

    private void addMenuInteractions() {
        interactions.addAction(ActionsMenu).whenTarget(BuildCancelButton).appliedTo(Target);
        interactions.addAction(BuildSimpleMenu).whenTarget(BuildSimpleButton).appliedTo(Target);
        interactions.addAction(BuildAdvancedMenu).whenTarget(BuildAdvancedButton).appliedTo(Target);
        interactions.addAction(IngameMenu).whenTarget(MenuPane).appliedTo(Target);
    }

    private void addTrainInteractions() {
        interactions.addAction(TrainFootman).whenTarget(TrainFootmanButton).whenSelected(Barracks).appliedTo(Selected);
        interactions.addAction(TrainPeasant).whenTarget(TrainPeasantButton).whenSelected(TownHall).appliedTo(Selected);

        interactions.addAction(TrainFootmanCancel).whenTarget(CancelButton).whenSelected(Barracks).withAction(TrainFootman).appliedTo(Selected);
        interactions.addAction(TrainPeasantCancel).whenTarget(CancelButton).whenSelected(TownHall).withAction(TrainPeasant).appliedTo(Selected);
    }

    private void addMoveInteractions() {
        interactions.addAction(MoveToLocation).whenTarget(Map).whenSelected(Footman).appliedTo(Selected);
        interactions.addAction(MoveToLocation).whenTarget(Map).whenSelected(Peasant).appliedTo(Selected);

        interactions.addAction(ConfirmLocation).whenTarget(Map).whenSelected(Footman).appliedTo(Selected).assignedTo(Parent).appliedAs(Addition);
        interactions.addAction(ConfirmLocation).whenTarget(Map).whenSelected(Peasant).appliedTo(Selected).assignedTo(Parent).appliedAs(Addition);

        interactions.addAction(MoveCancel).whenTarget(CancelButton).whenSelected(Footman).withAction(MoveToLocation).appliedTo(Selected);
        interactions.addAction(MoveCancel).whenTarget(CancelButton).whenSelected(Peasant).withAction(MoveToLocation).appliedTo(Selected);
    }

    private void addSelectionInteractions() {
        interactions.addAction(SelectToggle).whenTarget(Footman).appliedTo(Target).appliedAs(Addition);
        interactions.addAction(SelectToggle).whenTarget(Peasant).appliedTo(Target).appliedAs(Addition);
        interactions.addAction(SelectToggle).whenTarget(GoldMine).appliedTo(Target).appliedAs(Addition);
        interactions.addAction(SelectToggle).whenTarget(UnitType.TownHall).appliedTo(Target).appliedAs(Addition);
        interactions.addAction(SelectToggle).whenTarget(UnitType.Barracks).appliedTo(Target).appliedAs(Addition);
        interactions.addAction(SelectToggle).whenTarget(UnitType.Farm).appliedTo(Target).appliedAs(Addition);
    }
}
