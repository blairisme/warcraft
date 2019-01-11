package com.evilbird.warcraft.item.hud.actionpane;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemOperations;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.warcraft.action.identifier.CommonActionType;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.common.capability.ResourceRequirement;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.hud.HudControls;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.*;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.Objects.requireNonNull;

/**
 * Instances of this class show a grid of buttons representing the actions that
 * the selected items can perform.
 *
 * @author Blair Butterworth
 */
public class ActionPane extends GridPane
{
    private Collection<Item> selection;
    private Map<ResourceIdentifier, Float> resources;
    private ActionButtonProvider buttonProvider;
    private boolean cancelShown;

    @Inject
    public ActionPane(ActionButtonProvider buttonProvider)
    {
        super(3, 3);

        this.buttonProvider = buttonProvider;
        this.selection = Collections.emptyList();
        this.resources = Collections.emptyMap();
        this.cancelShown = false;

        setSize(176, 176);
        setCellPadding(3);
        setCellWidthMinimum(54);
        setCellHeightMinimum(46);
        setId(HudControls.ActionPane);
        setType(HudControls.ActionPane);
        setTouchable(Touchable.childrenOnly);
    }

    public void setSelection(Collection<Item> newSelection) {
        if (viewInvalidated(newSelection)) {
            System.out.println("actions updated");
            updateModel(newSelection);
            updateView(newSelection);
        }
    }

    private boolean viewInvalidated(Collection<Item> newSelection) {
        return selectionUpdated(newSelection) || actionsUpdated(newSelection) || resourcesUpdated(newSelection);
    }

    private boolean selectionUpdated(Collection<Item> newSelection) {
        return ! Objects.equals(selection, newSelection);
    }

    private boolean actionsUpdated(Collection<Item> newSelection) {
        boolean showCancel = hasAction(newSelection);
        return cancelShown != showCancel;
    }

    private boolean resourcesUpdated(Collection<Item> newSelection) {
        Map<ResourceIdentifier, Float> newResources = getResources(newSelection);
        return ! Objects.equals(resources, newResources);
    }

    private boolean hasAction(Collection<Item> selection) {
        if (selection.size() == 1) {
            Array<Action> actions = selection.iterator().next().getActions();
            return actions.size > 0;
        }
        return false;
    }

    private Map<ResourceIdentifier, Float> getResources(Collection<Item> selection) {
        if (! selection.isEmpty()) {
            Item item = selection.iterator().next();
            Item player = ItemOperations.findAncestorByType(item, DataType.Player);
            return new HashMap<>(((ResourceContainer)player).getResources());
        }
        return Collections.emptyMap();
    }

    private void updateModel(Collection<Item> newSelection) {
        selection = newSelection;
        resources = getResources(newSelection);
    }

    private void updateView(Collection<Item> selection) {
        clearCells();

        if (hasAction(selection)){
            showCancelAction();
        }
        else {
            showUnitActions(selection);
        }
    }

    private void showCancelAction() {
        ActionButton cancelButton = getButton(CommonActionType.Cancel);
        setCell(cancelButton, 2, 2);
        cancelShown = true;
    }

    private void showUnitActions(Collection<Item> selection) {
        Collection<ActionIdentifier> actions = getAvailableActions(selection);
        setCells(getTiles(actions));
        cancelShown = false;
    }

    private Collection<ActionIdentifier> getAvailableActions(Collection<Item> selection) {
        Collection<ActionIdentifier> result = new ArrayList<>();
        Iterator<Item> selectionIterator = selection.iterator();

        if (selectionIterator.hasNext()) {
            Item item = selectionIterator.next();
            result.addAll(getAvailableActions(item));

            while (selectionIterator.hasNext()) {
                item = selectionIterator.next();
                result.retainAll(getAvailableActions(item));
            }
        }
        return result;
    }

    private Collection<ActionIdentifier> getAvailableActions(Item item) {
        if (item instanceof Unit){
            Unit unit = (Unit)item;
            return unit.getAvailableActions();
        }
        return Collections.emptyList();
    }

    private Collection<Item> getTiles(Collection<ActionIdentifier> actions) {
        Collection<Item> result = new ArrayList<Item>(actions.size());
        for (ActionIdentifier action: actions){
            result.add(getButton(action));
        }
        return result;
    }

    private ActionButton getButton(ActionIdentifier action) {
        ActionButton result = buttonProvider.get(action);
        result.setTouchable(meetsResourceRequirements(action) ? Touchable.enabled : Touchable.disabled);
        return result;
    }

    private boolean meetsResourceRequirements(ActionIdentifier action) {
        if (action instanceof ResourceRequirement) {
            ResourceRequirement requirementAction = (ResourceRequirement)action;
            Map<ResourceIdentifier, Float> requirements = requirementAction.getResourceRequirements();

            for (Map.Entry<ResourceIdentifier, Float> requirement: requirements.entrySet()) {
                Float playerResource = requireNonNull(resources.get(requirement.getKey()), 0f);
                Float requiredResource = requirement.getValue();

                if (playerResource < requiredResource) {
                    return false;
                }
            }
        }
        return true;
    }
}
