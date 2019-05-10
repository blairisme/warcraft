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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.action.menu.MenuProvider;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.hud.HudControl;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.evilbird.warcraft.item.hud.control.actions.ActionButtonDetails.isEnabled;
import static com.evilbird.warcraft.item.hud.control.actions.ActionPaneView.Actions;

/**
 * Instances of this class show a grid of buttons representing the actions that
 * the selected items can perform.
 *
 * @author Blair Butterworth
 */
public class ActionPane extends GridItem implements MenuProvider
{
    private ActionPaneView view;
    private List<Item> selection;
    private Map<ResourceType, Float> resources;

    public ActionPane(Skin skin) {
        super(3, 3);
        this.view = Actions;
        this.selection = new ArrayList<>();
        this.resources = new HashMap<>();

        setSkin(skin);
        setBackground("action-panel");
        setSize(176, 176);
        setCellPadding(1);
        setCellWidth(54);
        setCellHeight(46);
        setIdentifier(HudControl.ActionPane);
        setType(HudControl.ActionPane);
        setTouchable(Touchable.childrenOnly);
    }

    public void setConstructing(Building building, boolean constructing) {
        if (selection.contains(building)) {
            updateView(constructing);
        }
    }

    public void setProducing(Building building, boolean producing) {
        if (selection.contains(building)) {
            updateView(producing);
        }
    }

    public void setPlaceholder(Item builder, boolean added) {
        if (selection.contains(builder)) {
            updateView(added);
        }
    }

    public void setSelected(Collection<Item> newSelection) {
        view = Actions;
        selection.clear();
        selection.addAll(newSelection);
        updateView();
    }

    public void setSelected(Item item, boolean selected) {
        view = Actions;
        if (selected) {
            selection.add(item);
        } else {
            selection.remove(item);
        }
        updateView();
    }

    public void setResource(ResourceType resource, float value) {
        resources.put(resource, value);
        updateView();
    }

    @Override
    public void showMenu(Identifier location) {
        view = (ActionPaneView)location;
        updateView();
    }

    private boolean showCancel() {
        if (selection.size() == 1) {
            Item selected = selection.get(0);
            if (selected instanceof Building) {
                Building building = (Building)selected;
                return building.isProducing() || building.isConstructing();
            }
            if (selected instanceof Gatherer) {
                Gatherer gatherer = (Gatherer)selected;
                return gatherer.isConstructing();
            }
        }
        return false;
    }

    private void updateView() {
        updateView(showCancel());
    }

    private void updateView(boolean showCancel) {
        clearItems();
        if (showCancel){
            addCancelButton();
        } else {
            addActionButtons();
        }
    }

    private void addCancelButton() {
        setAlignment(Alignment.BottomRight);
        ActionButton cancelButton = getButton(ActionButtonType.CancelButton);
        add(cancelButton);
    }

    private void addActionButtons() {
        setAlignment(Alignment.TopLeft);
        List<ActionButtonType> actions = getActions(selection);
        List<ActionButton> buttons = getButtons(actions);
        buttons.forEach(this::add);
    }

    private List<ActionButtonType> getActions(Collection<Item> selection) {
        switch (view) {
            case Actions: return getItemActions(selection);
            case SimpleBuildings: return ActionButtonDetails.getSimpleBuildingButtons();
            case AdvancedBuildings: return ActionButtonDetails.getAdvancedBuildingButtons();
            default: throw new UnsupportedOperationException();
        }
    }

    private List<ActionButtonType> getItemActions(Collection<Item> selection) {
        List<ActionButtonType> result = new ArrayList<>();
        Iterator<Item> selectionIterator = selection.iterator();

        if (selectionIterator.hasNext()) {
            Item item = selectionIterator.next();
            result.addAll(ActionButtonDetails.getActionButtons(item));

            while (selectionIterator.hasNext()) {
                item = selectionIterator.next();
                result.retainAll(ActionButtonDetails.getActionButtons(item));
            }
        }
        return result;
    }

    private List<ActionButton> getButtons(List<ActionButtonType> actions) {
        List<ActionButton> result = new ArrayList<>(actions.size());
        for (ActionButtonType action: actions){
            result.add(getButton(action));
        }
        return result;
    }

    private ActionButton getButton(ActionButtonType type) {
        ActionButton button = new ActionButton(getSkin());
        button.setType(type);
        button.setEnabled(isEnabled(type, resources));
        return button;
    }
}
