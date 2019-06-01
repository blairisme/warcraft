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
import com.evilbird.warcraft.action.select.SelectActions;
import com.evilbird.warcraft.item.placeholder.PlaceholderType;
import com.evilbird.warcraft.item.unit.UnitType;

import javax.inject.Inject;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.device.UserInputType.Drag;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.action.attack.AttackActions.AttackCancel;
import static com.evilbird.warcraft.action.attack.AttackActions.AttackMelee;
import static com.evilbird.warcraft.action.camera.CameraActions.Focus;
import static com.evilbird.warcraft.action.camera.CameraActions.Pan;
import static com.evilbird.warcraft.action.camera.CameraActions.Zoom;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmLocation;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmTarget;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructBarracks;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructBarracksCancel;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructFarm;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructFarmCancel;
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
import static com.evilbird.warcraft.action.move.MoveActions.MoveToLocation;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.AddBarracksPlaceholder;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.AddFarmPlaceholder;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.AddTownHallPlaceholder;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.PlaceholderCancel;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.PlaceholderMove;
import static com.evilbird.warcraft.action.select.SelectActions.SelectInvert;
import static com.evilbird.warcraft.action.train.TrainActions.TrainFootman;
import static com.evilbird.warcraft.action.train.TrainActions.TrainFootmanCancel;
import static com.evilbird.warcraft.action.train.TrainActions.TrainPeasant;
import static com.evilbird.warcraft.action.train.TrainActions.TrainPeasantCancel;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Target;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isConstructing;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isGathererConstructing;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isPlaceholder;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isPlaceholderClear;
import static com.evilbird.warcraft.item.data.DataType.Camera;
import static com.evilbird.warcraft.item.hud.HudControl.MenuPane;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildAdvancedButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildBarracksButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildFarmButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildSimpleButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.BuildTownHallButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.StopButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.TrainFootmanButton;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.TrainPeasantButton;
import static com.evilbird.warcraft.item.hud.control.status.selection.SelectionButtonType.FocusButton;
import static com.evilbird.warcraft.item.hud.control.status.selection.SelectionButtonType.UnselectButton;
import static com.evilbird.warcraft.item.layer.LayerType.Map;
import static com.evilbird.warcraft.item.layer.LayerType.Tree;
import static com.evilbird.warcraft.item.placeholder.PlaceholderType.BarracksPlaceholder;
import static com.evilbird.warcraft.item.placeholder.PlaceholderType.FarmPlaceholder;
import static com.evilbird.warcraft.item.placeholder.PlaceholderType.TownHallPlaceholder;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.GoldMine;
import static com.evilbird.warcraft.item.unit.UnitType.Grunt;
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
        attackInteraction(Footman, Grunt);
        attackInteraction(Peasant, Grunt);
    }

    private void attackInteraction(Identifier combatant, Identifier target) {
        interactions.addAction(AttackMelee, ConfirmTarget)
            .whenSelected(combatant)
            .whenTarget(target)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());

        interactions.addAction(AttackCancel)
            .whenSelected(combatant)
            .whenTarget(CancelButton)
            .withAction(AttackMelee)
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
        beginConstruction(ConstructBarracks, BarracksPlaceholder);
        beginConstruction(ConstructFarm, FarmPlaceholder);
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
        cancelConstruction(ConstructTownHallCancel, TownHall);
    }

    private void cancelConstruction(ActionIdentifier cancel, UnitType building) {
        interactions.addAction(cancel)
            .whenTarget(CancelButton)
            .whenSelected(both(withType(building), isConstructing()))
            .appliedTo(Selected);
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
        gatherInteraction(GatherGold, Peasant, GoldMine);
        gatherInteraction(GatherWood, Peasant, Tree);
    }

    private void gatherInteraction(ActionIdentifier action, Identifier gatherer, Identifier resource) {
        interactions.addAction(action, ConfirmTarget)
            .whenSelected(gatherer)
            .whenTarget(resource)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());

        interactions.addAction(GatherCancel)
            .whenSelected(gatherer)
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
        moveInteraction(Footman);
        moveInteraction(Peasant);
    }

    private void moveInteraction(Identifier movable) {
        interactions.addAction(MoveToLocation, ConfirmLocation)
            .whenSelected(movable)
            .whenTarget(Map)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());

        interactions.addAction(MoveCancel)
            .whenSelected(movable)
            .whenTarget(StopButton)
            .withAction(MoveToLocation)
            .appliedTo(Selected);
    }

    private void selectionInteractions() {
        selectInvert();
        selectArea();
        unselectButton();
    }

    private void selectInvert() {
        selectToggle(Grunt);
        selectToggle(Footman);
        selectToggle(Peasant);
        selectToggle(GoldMine);
        selectToggle(TownHall);
        selectToggle(Barracks);
        selectToggle(Farm);
    }
    
    private void selectToggle(Identifier selectable) {
        interactions.addAction(SelectInvert)
            .whenTarget(selectable)
            .appliedTo(Target)
            .appliedAs(Addition);
    }

    private void selectArea() {
        interactions.addAction(SelectActions.SelectBoxBegin)
                .forInput(UserInputType.SelectStart)
                .appliedTo(Target)
                .appliedAs(Addition);

        interactions.addAction(SelectActions.SelectBoxResize)
                .forInput(UserInputType.SelectResize)
                .appliedTo(Target)
                .appliedAs(Addition);

        interactions.addAction(SelectActions.SelectBoxEnd)
                .forInput(UserInputType.SelectStop)
                .appliedTo(Target)
                .appliedAs(Addition);
    }

    private void unselectButton() {
        interactions.addAction(SelectInvert)
                .whenTarget(UnselectButton)
                .appliedTo(targetParentItem(), selectedItem());
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
