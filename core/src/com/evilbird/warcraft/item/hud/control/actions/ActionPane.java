/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.actions;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.control.GridPane;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.menu.MenuProvider;
import com.evilbird.warcraft.item.hud.HudControl;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Instances of this class show a grid of buttons representing the actions that
 * the selected items can perform.
 *
 * @author Blair Butterworth
 */
//TODO: Disable buttons if insufficient resources
//TODO: Disable buttons if constructing
public class ActionPane extends GridPane implements MenuProvider
{
    private Collection<Item> selection;
//    private Terrain<ResourceIdentifier, Float> resources;
    private ActionButtonProvider buttonProvider;
    private ActionPaneLayout layout;
    private boolean cancelShown;
    private boolean invalidated;

    @Inject
    public ActionPane(ActionButtonProvider buttonProvider) {
        super(3, 3);

        this.buttonProvider = buttonProvider;
        this.selection = new ArrayList<>();
//        this.resources = Collections.emptyMap();
        this.layout = ActionPaneLayout.Actions;
        this.cancelShown = false;
        this.invalidated = true;

        setSize(176, 176);
        setCellPadding(3);
        setCellWidthMinimum(54);
        setCellHeightMinimum(46);
        setIdentifier(HudControl.ActionPane);
        setType(HudControl.ActionPane);
        setTouchable(Touchable.childrenOnly);
    }

    public void updateSelection(Collection<Item> newSelection) {
        selection.clear();
        selection.addAll(newSelection);
        layout = ActionPaneLayout.Actions;
        invalidated = true;
    }

    public void updateSelection(Item item, boolean selected) {
        layout = ActionPaneLayout.Actions;
        invalidated = true;
        if (selected) {
            selection.add(item);
        } else {
            selection.remove(item);
        }
    }

    @Override
    public void showMenu(Identifier location) {
        Validate.isInstanceOf(ActionPaneLayout.class, location);
        layout = (ActionPaneLayout)location;
        invalidated = true;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (invalidated) {
            invalidated = false;
            updateView();
        }
    }

    private void updateView() {
        clearCells();

        if (layout == ActionPaneLayout.Actions && hasAction(selection)){
            showCancelAction();
        }
        else {
            showActions(selection);
        }
    }

    private boolean hasAction(Collection<Item> selection) {
        if (selection.size() == 1) {
            return selection.iterator().next().hasActions();
        }
        return false;
    }

    private void showCancelAction() {
        ActionButton cancelButton = getButton(ActionButtonType.CancelButton);
        setCell(cancelButton, 2, 2);
        cancelShown = true;
    }

    private void showActions(Collection<Item> selection) {
        List<ActionButtonType> actions = getActionButtons(selection);
        setCells(getTiles(actions));
        cancelShown = false;
    }

    private List<ActionButtonType> getActionButtons(Collection<Item> selection) {
        switch (layout) {
            case Actions: return getItemActionButtons(selection);
            case SimpleBuildings: return ActionButtonAssociations.getSimpleBuildingButtons();
            case AdvancedBuildings: return ActionButtonAssociations.getAdvancedBuildingButtons();
            default: throw new UnsupportedOperationException();
        }
    }

    private List<ActionButtonType> getItemActionButtons(Collection<Item> selection) {
        List<ActionButtonType> result = new ArrayList<>();
        Iterator<Item> selectionIterator = selection.iterator();

        if (selectionIterator.hasNext()) {
            Item item = selectionIterator.next();
            result.addAll(ActionButtonAssociations.getActionButtons(item));

            while (selectionIterator.hasNext()) {
                item = selectionIterator.next();
                result.retainAll(ActionButtonAssociations.getActionButtons(item));
            }
        }
        return result;
    }

//    private Collection<ActionIdentifier> getAvailableActions(Item item) {
//        if (item instanceof Unit){
//            Unit unit = (Unit)item;
//            return unit.getAvailableActions();
//        }
//        return Collections.emptyList();
//    }

    private Collection<Item> getTiles(Collection<ActionButtonType> actions) {
        Collection<Item> result = new ArrayList<>(actions.size());
        for (ActionButtonType action: actions){
            result.add(getButton(action));
        }
        return result;
    }

    private ActionButton getButton(ActionButtonType action) {
        ActionButton result = buttonProvider.get(action);
        //result.setTouchable(meetsResourceRequirements(action) ? Touchable.enabled : Touchable.disabled);
        return result;
    }

//    private boolean meetsResourceRequirements(ActionIdentifier action) {
//        if (action instanceof ResourceRequirement) {
//            ResourceRequirement requirementAction = (ResourceRequirement)action;
//            Terrain<ResourceIdentifier, Float> requirements = requirementAction.getResourceRequirements();
//
//            for (Terrain.Entry<ResourceIdentifier, Float> requirement: requirements.entrySet()) {
//                Float playerResource = requireNonNull(resources.get(requirement.getKey()), 0f);
//                Float requiredResource = requirement.getValue();
//
//                if (playerResource < requiredResource) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }




//    private boolean viewInvalidated(Collection<Item> newSelection) {
//        return selectionUpdated(newSelection) || actionsUpdated(newSelection); //|| resourcesUpdated(newSelection);
//    }
//
//    private boolean selectionUpdated(Collection<Item> newSelection) {
//        return ! Objects.equals(selection, newSelection);
//    }
//
//    private boolean actionsUpdated(Collection<Item> newSelection) {
//        boolean showCancel = hasAction(newSelection);
//        return cancelShown != showCancel;
//    }
//
//    private boolean resourcesUpdated(Collection<Item> newSelection) {
//        Terrain<ResourceIdentifier, Float> newResources = getResources(newSelection);
//        return ! Objects.equals(resources, newResources);
//    }
//
//    private Terrain<ResourceIdentifier, Float> getResources(Collection<Item> selection) {
//        if (! selection.isEmpty()) {
//            Item item = selection.iterator().next();
//            Item player = ItemOperations.findAncestorByType(item, DataType.Player);
//            return new HashMap<>(((ResourceContainer)player).getResources());
//        }
//        return Collections.emptyMap();
//    }
//
//    private void resetLayout() {
//        layout = ActionPaneLayout.Actions;
//    }
//
//    private void updateModel(Collection<Item> newSelection) {
//        selection = newSelection;
//        resources = getResources(newSelection);
//    }
}
