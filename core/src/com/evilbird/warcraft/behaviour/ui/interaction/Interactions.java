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
import static com.evilbird.warcraft.action.camera.CameraActions.*;
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
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.*;
import static com.evilbird.warcraft.item.data.DataType.Camera;
import static com.evilbird.warcraft.item.hud.HudControl.MenuPane;
import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonType.*;
import static com.evilbird.warcraft.item.hud.control.status.selection.SelectionButtonType.FocusButton;
import static com.evilbird.warcraft.item.hud.control.status.selection.SelectionButtonType.UnselectButton;
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
        cancelConstruction(ConstructTownhallCancel, TownHall);
    }

    private void cancelConstruction(ActionIdentifier cancel, UnitType building) {
        interactions.addAction(cancel)
            .whenTarget(CancelButton)
            .whenSelected(both(withType(building), isUnderConstruction()))
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
        selectToggle();
        unselectButton();
    }

    private void selectToggle() {
        selectToggle(Footman);
        selectToggle(Peasant);
        selectToggle(GoldMine);
        selectToggle(TownHall);
        selectToggle(Barracks);
        selectToggle(Farm);
    }
    
    private void selectToggle(Identifier selectable) {
        interactions.addAction(SelectToggle)
            .whenTarget(selectable)
            .appliedTo(Target)
            .appliedAs(Addition);
    }

    private void unselectButton() {
        interactions.addAction(SelectToggle)
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
