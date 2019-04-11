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
import com.evilbird.warcraft.item.hud.HudControl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.evilbird.warcraft.item.hud.control.actions.ActionPaneView.Actions;

/**
 * Instances of this class show a grid of buttons representing the actions that
 * the selected items can perform.
 *
 * @author Blair Butterworth
 */
public class ActionPane extends GridItem implements MenuProvider
{
    private Collection<Item> selection;
    private ActionPaneView view;
    private boolean invalidated;

    public ActionPane(Skin skin) {
        super(3, 3);

        this.view = Actions;
        this.invalidated = true;
        this.selection = new ArrayList<>();

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

    public void updateSelection(Collection<Item> newSelection) {
        view = Actions;
        invalidated = true;
        selection.clear();
        selection.addAll(newSelection);
    }

    public void updateSelection(Item item, boolean selected) {
        view = Actions;
        invalidated = true;
        if (selected) {
            selection.add(item);
        } else {
            selection.remove(item);
        }
    }

    public void invalidateItem(Item item) {
        if (selection.contains(item)) {
            invalidated = true;
        }
    }

    @Override
    public void showMenu(Identifier location) {
        view = (ActionPaneView)location;
        invalidated = true;
    }

    @Override
    public void update(float delta) {
        if (invalidated) {
            invalidated = false;
            updateView();
        }
    }

    private void updateView() {
        clearItems();
        if (shouldShowCancel()){
            showCancel();
        } else {
            showActions();
        }
    }

    private boolean shouldShowCancel() {
        if (view == Actions && selection.size() == 1) {
            return selection.iterator().next().hasActions();
        }
        return false;
    }

    private void showCancel() {
        setAlignment(Alignment.BottomRight);
        ActionButton cancelButton = getButton(ActionButtonType.CancelButton);
        add(cancelButton);
    }

    private void showActions() {
        setAlignment(Alignment.TopLeft);
        List<ActionButtonType> actions = getActions(selection);
        List<ActionButton> buttons = getButtons(actions);
        buttons.forEach(this::add);
    }

    private List<ActionButtonType> getActions(Collection<Item> selection) {
        switch (view) {
            case Actions: return getItemActions(selection);
            case SimpleBuildings: return ActionBindings.getSimpleBuildingButtons();
            case AdvancedBuildings: return ActionBindings.getAdvancedBuildingButtons();
            default: throw new UnsupportedOperationException();
        }
    }

    private List<ActionButtonType> getItemActions(Collection<Item> selection) {
        List<ActionButtonType> result = new ArrayList<>();
        Iterator<Item> selectionIterator = selection.iterator();

        if (selectionIterator.hasNext()) {
            Item item = selectionIterator.next();
            result.addAll(ActionBindings.getActionButtons(item));

            while (selectionIterator.hasNext()) {
                item = selectionIterator.next();
                result.retainAll(ActionBindings.getActionButtons(item));
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

    private ActionButton getButton(ActionButtonType action) {
        ActionButton result = new ActionButton(getSkin());
        result.setType(action);
        //result.setTouchable(meetsResourceRequirements(action) ? Touchable.enabled : Touchable.disabled);
        return result;
    }
}
