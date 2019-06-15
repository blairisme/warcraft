/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.ui.placement.PlaceholderType;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;

import javax.inject.Inject;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.common.function.Predicates.not;
import static com.evilbird.engine.device.UserInputType.Drag;
import static com.evilbird.engine.device.UserInputType.SelectResize;
import static com.evilbird.engine.device.UserInputType.SelectStart;
import static com.evilbird.engine.device.UserInputType.SelectStop;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.action.attack.AttackActions.Attack;
import static com.evilbird.warcraft.action.attack.AttackActions.AttackCancel;
import static com.evilbird.warcraft.action.camera.CameraActions.Focus;
import static com.evilbird.warcraft.action.camera.CameraActions.Pan;
import static com.evilbird.warcraft.action.camera.CameraActions.Zoom;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmLocation;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmTarget;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructBarracks;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructBarracksCancel;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructFarm;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructFarmCancel;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructLumberMill;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructLumberMillCancel;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructTownHall;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructTownHallCancel;
import static com.evilbird.warcraft.action.gather.GatherActions.GatherCancel;
import static com.evilbird.warcraft.action.gather.GatherActions.GatherGold;
import static com.evilbird.warcraft.action.gather.GatherActions.GatherWood;
import static com.evilbird.warcraft.action.menu.MenuActions.ActionsMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.BuildAdvancedMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.BuildSimpleMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.IngameMenu;
import static com.evilbird.warcraft.action.move.MoveActions.MoveCancel;
import static com.evilbird.warcraft.action.move.MoveActions.MoveToItem;
import static com.evilbird.warcraft.action.move.MoveActions.MoveToLocation;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.AddBarracksPlaceholder;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.AddFarmPlaceholder;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.AddLumberMillPlaceholder;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.AddTownHallPlaceholder;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.PlaceholderCancel;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.PlaceholderMove;
import static com.evilbird.warcraft.action.select.SelectActions.SelectBoxBegin;
import static com.evilbird.warcraft.action.select.SelectActions.SelectBoxEnd;
import static com.evilbird.warcraft.action.select.SelectActions.SelectBoxResize;
import static com.evilbird.warcraft.action.select.SelectActions.SelectInvert;
import static com.evilbird.warcraft.action.train.TrainActions.TrainFootman;
import static com.evilbird.warcraft.action.train.TrainActions.TrainFootmanCancel;
import static com.evilbird.warcraft.action.train.TrainActions.TrainPeasant;
import static com.evilbird.warcraft.action.train.TrainActions.TrainPeasantCancel;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Target;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Singleton;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Water;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.associatedWith;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAi;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCombatant;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isConstructing;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isCorporeal;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isDestroyable;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isGatherer;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isMovable;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isMovableOver;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isPlaceholder;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isPlaceholderClear;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isResource;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isSelectable;
import static com.evilbird.warcraft.item.data.camera.CameraType.Camera;
import static com.evilbird.warcraft.item.layer.LayerType.Map;
import static com.evilbird.warcraft.item.layer.LayerType.Sea;
import static com.evilbird.warcraft.item.layer.LayerType.Tree;
import static com.evilbird.warcraft.item.ui.hud.HudControl.MenuPane;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildAdvancedButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildBarracksButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildFarmButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildLumberMillButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildSimpleButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildTownHallButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.StopButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TrainFootmanButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TrainPeasantButton;
import static com.evilbird.warcraft.item.ui.hud.control.status.selection.SelectionButtonType.FocusButton;
import static com.evilbird.warcraft.item.ui.hud.control.status.selection.SelectionButtonType.UnselectButton;
import static com.evilbird.warcraft.item.ui.placement.PlaceholderType.BarracksPlaceholder;
import static com.evilbird.warcraft.item.ui.placement.PlaceholderType.FarmPlaceholder;
import static com.evilbird.warcraft.item.ui.placement.PlaceholderType.LumberMillPlaceholder;
import static com.evilbird.warcraft.item.ui.placement.PlaceholderType.TownHallPlaceholder;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.CircleOfPower;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcherCaptive;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.GoldMine;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;

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
        attackInteractions();
        menuInteractions();
        cameraInteractions();
        constructionInteractions();
        gatherInteractions();
        trainInteractions();
        moveInteractions();
        selectionInteractions();
    }

    public Interaction getInteraction(UserInput input, Item item, Item selected) {
        return interactions.getInteraction(input, item, selected);
    }

    private void attackInteractions() {
        interactions.addAction(Attack, ConfirmTarget)
            .whenSelected(both(isCorporeal(), isCombatant()))
            .whenTarget(isAi().and(isDestroyable()).and(not(isResource())))
            .appliedTo(Selected)
            .appliedAs(confirmedAction());

        interactions.addAction(AttackCancel)
            .whenSelected(both(isCorporeal(), isCombatant()))
            .whenTarget(CancelButton)
            .withAction(Attack)
            .appliedTo(Selected);
    }

    private void constructionInteractions() {
        addPlaceholder();
        cancelPlaceholder();
        dragPlaceholder();
        beginConstruction();
        cancelConstruction();
    }

    public void addPlaceholder() {
        addPlaceholder(AddBarracksPlaceholder, BuildBarracksButton);
        addPlaceholder(AddLumberMillPlaceholder, BuildLumberMillButton);
        addPlaceholder(AddFarmPlaceholder, BuildFarmButton);
        addPlaceholder(AddTownHallPlaceholder, BuildTownHallButton);
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
            .whenSelected(both(withType(Peasant), associatedWith(isPlaceholder())))
            .appliedTo(Selected);
    }

    private void dragPlaceholder() {
        interactions.addAction(PlaceholderMove)
            .forInput(Drag)
            .whenTarget(isPlaceholder())
            .appliedTo(Target);
    }

    private void beginConstruction() {
        beginConstruction(ConstructBarracks, BarracksPlaceholder);
        beginConstruction(ConstructFarm, FarmPlaceholder);
        beginConstruction(ConstructLumberMill, LumberMillPlaceholder);
        beginConstruction(ConstructTownHall, TownHallPlaceholder);
    }

    private void beginConstruction(ActionIdentifier construction, PlaceholderType placeholder) {
        interactions.addAction(construction)
            .whenTarget(both(withType(placeholder), isPlaceholderClear()))
            .whenSelected(Peasant)
            .appliedTo(Selected);
    }

    private void cancelConstruction() {
        cancelConstruction(ConstructBarracksCancel, Barracks);
        cancelConstruction(ConstructFarmCancel, Farm);
        cancelConstruction(ConstructLumberMillCancel, LumberMill);
        cancelConstruction(ConstructTownHallCancel, TownHall);
    }

    private void cancelConstruction(ActionIdentifier cancelAction, UnitType building) {
        interactions.addAction(cancelAction)
            .whenTarget(CancelButton)
            .whenSelected(both(withType(building), isConstructing()))
            .appliedTo(
                (target, selected) -> selected,
                (target, selected) -> ((Unit)selected).getAssociatedItem());

        interactions.addAction(cancelAction)
            .whenTarget(CancelButton)
            .whenSelected(both(isGatherer(), associatedWith(building)))
            .appliedTo(
                (target, selected) -> ((Unit)selected).getAssociatedItem(),
                (target, selected) -> selected);
    }

    private void cameraInteractions() {
        interactions.addAction(Pan)
            .forInput(Drag)
            .whenTarget(Camera)
            .appliedTo(Target);

        interactions.addAction(Zoom)
            .forInput(UserInputType.Zoom)
            .whenTarget(Camera)
            .appliedTo(Target);

        interactions.addAction(Focus)
            .whenTarget(FocusButton)
            .appliedTo(targetParentItem(), selectedItem());
    }

    private void gatherInteractions() {
        gatherInteraction(GatherGold, GoldMine);
        gatherInteraction(GatherWood, Tree);
    }

    private void gatherInteraction(ActionIdentifier action, Identifier resource) {
        interactions.addAction(action, ConfirmTarget)
            .whenSelected(both(isCorporeal(), isGatherer()))
            .whenTarget(resource)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());

        interactions.addAction(GatherCancel)
            .whenSelected(both(isCorporeal(), isGatherer()))
            .whenTarget(CancelButton)
            .withAction(action)
            .appliedTo(Selected);
    }

    private void menuInteractions() {
        interactions.addAction(ActionsMenu)
            .whenTarget(BuildCancelButton)
            .appliedTo(Target);

        interactions.addAction(BuildSimpleMenu)
            .whenTarget(BuildSimpleButton)
            .appliedTo(Target);

        interactions.addAction(BuildAdvancedMenu)
            .whenTarget(BuildAdvancedButton)
            .appliedTo(Target);

        interactions.addAction(IngameMenu)
            .whenTarget(MenuPane)
            .appliedTo(Target);
    }

    private void trainInteractions() {
        trainInteraction(TrainFootman, TrainFootmanCancel, TrainFootmanButton, Barracks);
        trainInteraction(TrainPeasant, TrainPeasantCancel, TrainPeasantButton, TownHall);
    }

    private void trainInteraction(
        ActionIdentifier action,
        ActionIdentifier cancel,
        Identifier build,
        Identifier building)
    {
        interactions.addAction(action)
            .whenSelected(building)
            .whenTarget(build)
            .appliedTo(Selected);

        interactions.addAction(cancel)
            .whenSelected(building)
            .whenTarget(CancelButton)
            .withAction(action)
            .appliedTo(Selected);
    }

    private void moveInteractions() {
        interactions.addAction(MoveToLocation, ConfirmLocation)
            .whenSelected(both(isCorporeal(), isMovableOver(Land)))
            .whenTarget(Map)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());

        interactions.addAction(MoveToLocation, ConfirmLocation)
            .whenSelected(both(isCorporeal(), isMovableOver(Water)))
            .whenTarget(Sea)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());

        interactions.addAction(MoveCancel)
            .whenSelected(both(isCorporeal(), isMovable()))
            .whenTarget(StopButton)
            .withAction(MoveToLocation)
            .appliedTo(Selected);

        interactions.addAction(MoveToItem, ConfirmLocation)
            .whenSelected(CircleOfPower)
            .whenTarget(ElvenArcherCaptive)
            .appliedTo(Target)
            .appliedAs(confirmedAction());
    }

    private void selectionInteractions() {
        selectInvert();
        selectArea();
        deselectButton();
    }

    private void selectInvert() {
        interactions.addAction(SelectInvert)
            .whenTarget(isSelectable())
            .appliedTo(Target)
            .appliedAs(Addition);
    }

    private void selectArea() {
        interactions.addAction(SelectBoxBegin)
            .forInput(SelectStart)
            .appliedTo(Target)
            .appliedAs(Addition);

        interactions.addAction(SelectBoxResize)
            .forInput(SelectResize)
            .appliedTo(Target)
            .appliedAs(Addition);

        interactions.addAction(SelectBoxEnd)
            .forInput(SelectStop)
            .appliedTo(Target)
            .appliedAs(Addition);
    }

    private void deselectButton() {
        interactions.addAction(SelectInvert)
            .whenTarget(UnselectButton)
            .appliedTo(targetParentItem(), selectedItem())
            .appliedAs(Singleton);
    }

    private BiConsumer<Item, Action> confirmedAction() {
        return (subject, action) -> {
            Identifier id = action.getIdentifier();
            if (id == ConfirmTarget || id == ConfirmLocation) {
                Item parent = subject.getParent();
                parent.addAction(action);
            } else {
                subject.clearActions();
                subject.addAction(action);
            }
        };
    }

    private BiFunction<Item, Item, Item> targetParentItem() {
        return (target, selected) -> {
            Item parent = target.getParent();
            if (parent instanceof Supplier) {
                Supplier supplier = (Supplier)parent;
                Object recipient = supplier.get();
                if (recipient instanceof Item) {
                    return (Item)recipient;
                }
            }
            return target;
        };
    }

    private BiFunction<Item, Item, Item> selectedItem() {
        return (target, selected) -> selected;
    }
}
