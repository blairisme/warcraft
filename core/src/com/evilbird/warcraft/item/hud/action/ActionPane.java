package com.evilbird.warcraft.item.hud.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.hud.HudControls;
import com.evilbird.warcraft.item.hud.building.BuildSite;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.building.Building;

import java.util.*;

import javax.inject.Inject;

/**
 * Instances of this class show a grid of buttons representing the actions that
 * can the selected items can perform.
 *
 * @author Blair Butterworth
 */
public class ActionPane extends GridPane
{
    private ActionButtonProvider buttonProvider;
    private Collection<Item> selection;
    private boolean cancelShown;

    @Inject
    public ActionPane(ActionButtonProvider buttonProvider)
    {
        super(3, 3);

        this.buttonProvider = buttonProvider;
        this.selection = Collections.emptyList();
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
        boolean viewInvalidated = ! Objects.equals(selection, newSelection);
        boolean viewRequiresCancel = requiresCancelAction(selection);
        boolean viewUpdated = viewRequiresCancel != cancelShown;

        if (viewInvalidated || viewUpdated) {
            selection = newSelection;
            clearCells();

            if (viewRequiresCancel){
                showCancelAction();
            }
            else {
                showUnitActions(selection);
            }
        }
    }

    private boolean requiresCancelAction(Collection<Item> selection) {
        if (selection.size() == 1) {
            Array<Action> actions = selection.iterator().next().getActions();
            if (actions.size > 0) {
                return true;
            }
        }
        return false;
    }

    private void showCancelAction() {
        ActionButton cancelButton = getButton(ActionType.Cancel);
        setCell(cancelButton, 2, 2);
        cancelShown = true;
    }

    private void showUnitActions(Collection<Item> selection) {
        Collection<ActionType> actions = getAvailableActions(selection);
        setCells(getTiles(actions));
        cancelShown = false;
    }

    private Collection<ActionType> getAvailableActions(Collection<Item> selection) {
        Collection<ActionType> result = selection.isEmpty() ? EnumSet.noneOf(ActionType.class) : EnumSet.allOf(ActionType.class);
        for (Item item: selection){
            Collection<ActionType> actions = getAvailableActions(item);
            result.retainAll(actions);
        }
        return result;
    }

    private Collection<ActionType> getAvailableActions(Item item) {
        if (item instanceof Unit){
            Unit unit = (Unit)item;
            return unit.getAvailableActions();
        }
        return Collections.emptyList();
    }

    private Collection<Item> getTiles(Collection<ActionType> actions) {
        Collection<Item> result = new ArrayList<Item>(actions.size());
        for (ActionType action: actions){
            result.add(getButton(action));
        }
        return result;
    }

    private ActionButton getButton(ActionType action) {
        ActionButton result = buttonProvider.get();
        result.setAction(action);
        result.setSize(54, 46);
        return result;
    }
}
