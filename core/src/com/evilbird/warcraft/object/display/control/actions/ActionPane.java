/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.control.Grid;
import com.evilbird.warcraft.action.menu.MenuProvider;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.display.HudControl;
import com.evilbird.warcraft.object.display.control.actions.buttons.ButtonController;
import com.evilbird.warcraft.object.display.control.actions.buttons.ButtonControllers;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.evilbird.warcraft.object.common.query.UnitPredicates.isCorporeal;

/**
 * Instances of this class show a grid of buttons representing the actions that
 * the selected items can perform.
 *
 * @author Blair Butterworth
 */
public class ActionPane extends Grid implements MenuProvider
{
    private ActionPaneView view;
    private List<GameObject> selection;
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

    public void setPlaceholder(GameObject builder, boolean added) {
        if (selection.contains(builder)) {
            updateView(added);
        }
    }

    public Collection<GameObject> getSelected() {
        return selection;
    }

    public void setSelected(Collection<GameObject> newSelection) {
        view = ActionPaneView.Actions;
        selection.clear();
        selection.addAll(newSelection);
        updateView();
    }

    public void setSelected(GameObject gameObject, boolean selected) {
        view = ActionPaneView.Actions;
        if (selected) {
            selection.add(gameObject);
        } else {
            selection.remove(gameObject);
        }
        updateView();
    }

    public float getResource(ResourceType resource) {
        return resources.get(resource);
    }

    public void setPlayerResource(ResourceType resource, float value) {
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
            GameObject selected = selection.get(0);
            if (selected instanceof Building) {
                Building building = (Building)selected;
                return building.isProducing() || building.isConstructing();
            }
            if (selected instanceof Gatherer) {
                Gatherer gatherer = (Gatherer)selected;
                return gatherer.getConstruction() != null;
            }
        }
        return false;
    }

    private void updateView() {
        updateView(showCancel());
    }

    private void updateView(boolean showCancel) {
        removeObjects();
        if (onlyCorporealSelected()) {
            populateView(showCancel);
        }
    }

    private boolean onlyCorporealSelected() {
        return CollectionUtils.containsAll(selection, isCorporeal());
    }

    private void populateView(boolean showCancel) {
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
        CollectionUtils.forEach(buttons, this::add);
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

    private List<ActionButton> getButtons(ActionPaneView view, Collection<GameObject> gameObjects) {
        List<ActionButton> result = new ArrayList<>();
        if (gameObjects.size() == 1) {
            return getButtons(view, gameObjects.iterator().next());
        }
        return result;
    }

    private List<ActionButton> getButtons(ActionPaneView view, GameObject gameObject) {
        ButtonController controller = buttons.getButtonController(gameObject, view);
        return getButtons(controller, gameObject);
    }

    private List<ActionButton> getButtons(ButtonController controller, GameObject gameObject) {
        List<ActionButtonType> types = controller.getButtons(gameObject);
        List<ActionButton> buttons = new ArrayList<>(types.size());
        for (ActionButtonType type: types){
            buttons.add(getButton(type, controller, (Unit) gameObject));
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
