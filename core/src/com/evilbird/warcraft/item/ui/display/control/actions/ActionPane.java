/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.action.menu.MenuProvider;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.ui.display.HudControl;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.ButtonController;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.ButtonControllers;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    private ButtonControllers buttons;

    public ActionPane(Skin skin) {
        super(3, 3);
        this.view = ActionPaneView.Actions;
        this.selection = new ArrayList<>();
        this.resources = new HashMap<>();
        this.buttons = new ButtonControllers();

        setSkin(skin);
        setBackground("action-panel");
        setSize(176, 176);
        setCellPadding(1);
        setCellWidth(54);
        setCellHeight(46);
        setIdentifier(HudControl.ActionPane);
        setType(HudControl.ActionPane);
        setTouchable(Touchable.enabled);
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

    public Collection<Item> getSelected() {
        return selection;
    }

    public void setSelected(Collection<Item> newSelection) {
        view = ActionPaneView.Actions;
        selection.clear();
        selection.addAll(newSelection);
        updateView();
    }

    public void setSelected(Item item, boolean selected) {
        view = ActionPaneView.Actions;
        if (selected) {
            selection.add(item);
        } else {
            selection.remove(item);
        }
        updateView();
    }

    public float getResource(ResourceType resource) {
        return resources.get(resource);
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
                return gatherer.getAssociatedItem() != null;
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
        List<ActionButton> buttons = getButtons();
        buttons.forEach(this::add);
    }

    private List<ActionButton> getButtons() {
        if (view  == ActionPaneView.Actions) {
            return getButtons(view, selection);
        }
        if (! selection.isEmpty()) {
            return getButtons(view, selection.iterator().next());
        }
        return Collections.emptyList();
    }

    private List<ActionButton> getButtons(ActionPaneView view, Item item) {
        ButtonController controller = buttons.getButtonController(item, view);
        return getButtons(controller, item);
    }

    private List<ActionButton> getButtons(ActionPaneView view, Collection<Item> items) {
        List<ActionButton> result = new ArrayList<>();
        Iterator<Item> itemsIterator = items.iterator();

        if (itemsIterator.hasNext()) {
            Item item = itemsIterator.next();
            ButtonController controller = buttons.getButtonController(item, view);
            result.addAll(getButtons(controller, item));

            while (itemsIterator.hasNext()) {
                item = itemsIterator.next();
                controller = buttons.getButtonController(item, view);
                result.retainAll(getButtons(controller, item));
            }
        }
        return result;
    }

    private List<ActionButton> getButtons(ButtonController controller, Item item) {
        List<ActionButtonType> types = controller.getButtons(item);
        List<ActionButton> buttons = new ArrayList<>(types.size());
        for (ActionButtonType type: types){
            buttons.add(getButton(type, controller, (Unit)item));
        }
        return buttons;
    }

    private ActionButton getButton(ActionButtonType type, ButtonController controller, Unit unit) {
        ActionButton button = new ActionButton(getSkin());
        button.setType(type, unit);
        button.setEnabled(controller.getEnabled(type, unit));
        return button;
    }

    private ActionButton getButton(ActionButtonType buttonType) {
        ActionButton button = new ActionButton(getSkin());
        button.setType(buttonType);
        button.setEnabled(true);
        return button;
    }
}