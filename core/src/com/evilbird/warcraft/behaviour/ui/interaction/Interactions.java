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
import com.evilbird.warcraft.action.confirm.ConfirmActions;
import com.evilbird.warcraft.action.construct.ConstructActions;
import com.evilbird.warcraft.action.placeholder.PlaceholderActions;
import com.evilbird.warcraft.action.produce.ProduceUnitActions;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.placement.PlaceholderType;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;

import javax.inject.Inject;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.evilbird.engine.common.function.Predicates.all;
import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.common.function.Predicates.nonNull;
import static com.evilbird.engine.common.function.Predicates.not;
import static com.evilbird.engine.device.UserInputType.Drag;
import static com.evilbird.engine.device.UserInputType.Menu;
import static com.evilbird.engine.device.UserInputType.PressDown;
import static com.evilbird.engine.device.UserInputType.PressDrag;
import static com.evilbird.engine.device.UserInputType.PressUp;
import static com.evilbird.engine.item.utility.ItemPredicates.hasType;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.action.attack.AttackActions.Attack;
import static com.evilbird.warcraft.action.attack.AttackActions.AttackCancel;
import static com.evilbird.warcraft.action.camera.CameraActions.Focus;
import static com.evilbird.warcraft.action.camera.CameraActions.Pan;
import static com.evilbird.warcraft.action.camera.CameraActions.Zoom;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmAttack;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmLocation;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmTarget;
import static com.evilbird.warcraft.action.gather.GatherActions.GatherCancel;
import static com.evilbird.warcraft.action.gather.GatherActions.GatherGold;
import static com.evilbird.warcraft.action.gather.GatherActions.GatherOil;
import static com.evilbird.warcraft.action.gather.GatherActions.GatherWood;
import static com.evilbird.warcraft.action.menu.MenuActions.ActionsMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.BuildAdvancedMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.BuildSimpleMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.IngameMenu;
import static com.evilbird.warcraft.action.move.MoveActions.MoveCancel;
import static com.evilbird.warcraft.action.move.MoveActions.MoveToItem;
import static com.evilbird.warcraft.action.move.MoveActions.MoveToLocation;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.PlaceholderCancel;
import static com.evilbird.warcraft.action.placeholder.PlaceholderActions.PlaceholderMove;
import static com.evilbird.warcraft.action.produce.ProduceUpgradeActions.RangedDamage1Upgrade;
import static com.evilbird.warcraft.action.produce.ProduceUpgradeActions.RangedDamage1UpgradeCancel;
import static com.evilbird.warcraft.action.select.SelectActions.SelectBoxBegin;
import static com.evilbird.warcraft.action.select.SelectActions.SelectBoxEnd;
import static com.evilbird.warcraft.action.select.SelectActions.SelectBoxResize;
import static com.evilbird.warcraft.action.select.SelectActions.SelectInvert;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Selected;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Target;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Singleton;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Standalone;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Water;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.associatedWith;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAi;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAttacker;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isBuilding;
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
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isSelected;
import static com.evilbird.warcraft.item.data.camera.CameraType.Camera;
import static com.evilbird.warcraft.item.layer.LayerType.Map;
import static com.evilbird.warcraft.item.layer.LayerType.OpaqueFogSection;
import static com.evilbird.warcraft.item.layer.LayerType.Sea;
import static com.evilbird.warcraft.item.layer.LayerType.Shore;
import static com.evilbird.warcraft.item.layer.LayerType.Tree;
import static com.evilbird.warcraft.item.layer.LayerType.WallSection;
import static com.evilbird.warcraft.item.ui.display.HudControl.MenuPane;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildAdvancedButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildSimpleButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.ImprovedRangedUpgradeButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.StopButton;
import static com.evilbird.warcraft.item.ui.display.control.status.selection.SelectionButtonType.FocusButton;
import static com.evilbird.warcraft.item.ui.display.control.status.selection.SelectionButtonType.UnselectButton;
import static com.evilbird.warcraft.item.unit.UnitType.CircleOfPower;
import static com.evilbird.warcraft.item.unit.UnitType.GoldMine;
import static com.evilbird.warcraft.item.unit.UnitType.OilPatch;
import static com.evilbird.warcraft.item.unit.UnitType.OilPlatform;

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
        moveInteractions();
        attackInteractions();
        gatherInteractions();
        buildInteractions();
        trainInteractions();
        upgradeInteractions();
        menuInteractions();
        cameraInteractions();
        selectInteractions();
    }

    public Interaction getInteraction(UserInput input, Item item, Item selected) {
        return interactions.getInteraction(input, item, selected);
    }

    private void attackInteractions() {
        interactions.addAction(Attack, ConfirmAttack)
            .whenSelected(both(isCorporeal(), isAttacker()))
            .whenTarget(isAi().and(isDestroyable()).and(not(isResource())))
            .appliedTo(Selected)
            .appliedAs(confirmedAction());

        interactions.addAction(Attack, ConfirmAttack)
            .whenSelected(both(isCorporeal(), isAttacker()))
            .whenTarget(WallSection)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());

        interactions.addAction(AttackCancel)
            .whenSelected(both(isCorporeal(), isAttacker()))
            .whenTarget(CancelButton)
            .withAction(Attack)
            .appliedTo(Selected);
    }

    private void buildInteractions() {
        addPlaceholder();
        cancelPlaceholder();
        dragPlaceholder();
        beginConstruction();
        cancelConstruction();
    }

    private void addPlaceholder() {
        for (ActionButtonType button: ActionButtonType.values()) {
            if (button.isBuildButton()) {
                UnitType building = button.getBuildProduct();
                PlaceholderType placeholder = PlaceholderType.forBuilding(building);
                PlaceholderActions action = PlaceholderActions.forPlaceholder(placeholder);
                addPlaceholder(button, action);
            }
        }
    }

    private void addPlaceholder(ActionButtonType button, ActionIdentifier action) {
        interactions.addAction(action)
            .whenTarget(button)
            .whenSelected(isGatherer())
            .appliedTo(Selected)
            .appliedAs(Addition);
    }

    private void cancelPlaceholder() {
        interactions.addAction(PlaceholderCancel)
            .whenTarget(CancelButton)
            .whenSelected(both(isGatherer(), associatedWith(isPlaceholder())))
            .appliedTo(Selected)
            .appliedAs(Addition);

        interactions.addAction(PlaceholderCancel, SelectInvert)
            .whenSelected(both(isSelected(), associatedWith(isPlaceholder())))
            .whenTarget(isGatherer())
            .appliedTo(Selected)
            .appliedAs(Addition);
    }

    private void dragPlaceholder() {
        interactions.addAction(PlaceholderMove)
            .forInput(Drag)
            .whenTarget(isPlaceholder())
            .appliedTo(Target);
    }

    private void beginConstruction() {
        for (PlaceholderType placeholder: PlaceholderType.values()) {
            UnitType building = placeholder.getBuilding();
            ConstructActions action = ConstructActions.forProduct(building);
            beginConstruction(placeholder, action, building);
        }
    }

    private void beginConstruction(PlaceholderType placeholder, ConstructActions action, UnitType building) {
        interactions.addAction(action)
            .whenTarget(both(withType(placeholder), isPlaceholderClear()))
            .whenSelected(isGatherer())
            .appliedTo(Selected);

        interactions.addAction(action)
            .whenSelected(both(isGatherer(), associatedWith(building)))
            .whenTarget(both(isConstructing(), withType(building)))
            .appliedTo(Selected);
    }

    private void cancelConstruction() {
        for (ConstructActions constructAction: ConstructActions.values()) {
            if (constructAction.isCancel()) {
                cancelConstruction(constructAction, constructAction.getProduct());
            }
        }
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
        gatherInteraction(GatherOil, OilPlatform);
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

    private void gatherOil(ActionIdentifier action, Identifier resource) {
        interactions.addAction(action, ConfirmTarget)
            .whenSelected(all(isCorporeal(), isGatherer()))
            .whenTarget(resource)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());

        interactions.addAction(GatherCancel)
            .whenSelected(both(isCorporeal(), isGatherer()))
            .whenTarget(CancelButton)
            .withAction(GatherOil)
            .appliedTo(Selected);
    }

    private void menuInteractions() {
        buildMenuInteractions();
        ingameMenuInteractions();
    }

    private void buildMenuInteractions() {
        interactions.addAction(ActionsMenu)
            .whenTarget(BuildCancelButton)
            .appliedTo(Target)
            .appliedAs(Addition);

        interactions.addAction(BuildSimpleMenu)
            .whenTarget(BuildSimpleButton)
            .appliedTo(Target)
            .appliedAs(Addition);

        interactions.addAction(BuildAdvancedMenu)
            .whenTarget(BuildAdvancedButton)
            .appliedTo(Target)
            .appliedAs(Addition);
    }

    private void ingameMenuInteractions() {
        interactions.addAction(IngameMenu)
            .whenTarget(MenuPane)
            .appliedTo(Target)
            .appliedAs(Addition);

        interactions.addAction(IngameMenu)
            .forInput(Menu)
            .assignedTo(item -> null)
            .appliedTo((t, s) -> null, (t, s) -> null)
            .appliedAs(Standalone);
    }

    private void trainInteractions() {
        for (ActionButtonType button: ActionButtonType.values()) {
            if (button.isTrainButton()) {
                UnitType product = button.getTrainProduct();
                ProduceUnitActions train = ProduceUnitActions.forProduct(product);
                ProduceUnitActions cancel = ProduceUnitActions.forProductCancel(product);
                productionInteraction(button, train, cancel);
            }
        }
    }

    private void upgradeInteractions() {
        productionInteraction(
            ImprovedRangedUpgradeButton,
                RangedDamage1Upgrade,
                RangedDamage1UpgradeCancel);
    }

    private void productionInteraction(
        ActionButtonType button,
        ActionIdentifier action,
        ActionIdentifier cancel)
    {
        interactions.addAction(action)
            .whenSelected(isBuilding())
            .whenTarget(button)
            .appliedTo(Selected);

        interactions.addAction(cancel)
            .whenSelected(isBuilding())
            .whenTarget(CancelButton)
            .withAction(action)
            .appliedTo(Selected);
    }

    private void moveInteractions() {
        moveToLocationInteractions();
        moveToItemInteractions();
        moveCancelInteraction();
    }

    private void moveToLocationInteractions() {
        interactions.addAction(MoveToLocation, ConfirmLocation)
            .whenSelected(both(isCorporeal(), isMovableOver(Land)))
            .whenTarget(hasType(Map, Shore, OpaqueFogSection))
            .appliedTo(Selected)
            .appliedAs(confirmedAction());

        interactions.addAction(MoveToLocation, ConfirmLocation)
            .whenSelected(both(isCorporeal(), isMovableOver(Water)))
            .whenTarget(hasType(Sea, OpaqueFogSection))
            .appliedTo(Selected)
            .appliedAs(confirmedAction());
    }

    private void moveToItemInteractions() {
        interactions.addAction(MoveToItem, ConfirmLocation)
            .whenSelected(both(isCorporeal(), isMovableOver(Land)))
            .whenTarget(CircleOfPower)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());

        interactions.addAction(MoveToItem, ConfirmLocation)
            .whenSelected(both(isCorporeal(), isMovableOver(Water)))
            .whenTarget(OilPatch)
            .appliedTo(Selected)
            .appliedAs(confirmedAction());
    }

    private void moveCancelInteraction() {
        interactions.addAction(MoveCancel)
            .whenSelected(both(isCorporeal(), isMovable()))
            .whenTarget(StopButton)
            .withAction(MoveToLocation)
            .appliedTo(Selected);
    }

    private void selectInteractions() {
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
            .forInput(PressDown)
            .whenTarget(nonNull())
            .appliedTo(Target)
            .appliedAs(Addition);

        interactions.addAction(SelectBoxResize)
            .forInput(PressDrag)
            .whenTarget(nonNull())
            .appliedTo(Target)
            .appliedAs(Addition);

        interactions.addAction(SelectBoxEnd)
            .forInput(PressUp)
            .whenTarget(nonNull())
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
            if (id instanceof ConfirmActions) {
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
