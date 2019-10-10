/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.common.state.SelectableObject;
import com.evilbird.warcraft.item.ui.selection.SelectionBox;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.item.utility.ItemPredicates.overlapping;
import static com.evilbird.engine.item.utility.ItemPredicates.touchable;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isSelected;
import static com.evilbird.warcraft.item.ui.selection.SelectionType.SelectionBox;

/**
 * Instances of this {@link Action} manipulate a {@link SelectionBox}, a user
 * define graphical area that denotes the items the user wishes to select.
 *
 * @author Blair Butterworth
 */
public class SelectArea extends BasicAction
{
    private transient Events events;

    @Inject
    public SelectArea(EventQueue events) {
        this.events = events;
    }

    @Override
    public boolean act(float delta) {
        Item item = getItem();
        ItemRoot root = item.getRoot();

        switch((SelectActions)getIdentifier()) {
            case SelectBoxBegin: return addBox(root);
            case SelectBoxResize: return updateBox(root);
            case SelectBoxEnd: return removeBox(root);
            default: throw new UnsupportedOperationException();
        }
    }

    private boolean addBox(ItemRoot root) {
        getBox(root);
        return true;
    }

    public Item getBox(ItemRoot root) {
        Item box = root.find(withType(SelectionBox));
        if (box == null) {
            UserInput input = getCause();
            box = new SelectionBox();
            box.setPosition(root.unproject(input.getPosition()));
            box.setSize(new Vector2(1, 1));
            root.addItem(box);
        }
        return box;
    }

    private boolean updateBox(ItemRoot root) {
        Item box = getBox(root);
        UserInput input = getCause();

        Vector2 point1 = root.unproject(input.getPosition());
        Vector2 point2 = root.unproject(input.getDelta());

        Vector2 position = new Vector2(Math.min(point1.x, point2.x), Math.min(point1.y, point2.y));
        Vector2 size = new Vector2(Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));

        box.setPosition(position);
        box.setSize(size);

        return true;
    }

    private boolean removeBox(ItemRoot root) {
        Item box = getBox(root);
        root.removeItem(box);

        Collection<Item> overlapping = root.findAll(both(touchable(), overlapping(box)));
        Collection<Item> oldSelection = root.findAll(isSelected());
        Collection<Item> newSelection = new ArrayList<>(overlapping);

        newSelection.removeAll(oldSelection);
        oldSelection.removeAll(overlapping);

        select(newSelection);
        deselect(oldSelection);

        return true;
    }

    private void deselect(Collection<Item> items) {
        for (Item item: items) {
            deselect(item);
        }
    }

    private void deselect(Item item) {
        if (item instanceof SelectableObject) {
            SelectableObject selectable = (SelectableObject)item;
            if (selectable.getSelectable() && selectable.getSelected()) {
                selectable.setSelected(false);
                events.add(new SelectEvent(selectable, false));
            }
        }
    }

    private void select(Collection<Item> items) {
        for (Item item: items) {
            select(item);
        }
    }

    private void select(Item item) {
        if (item instanceof Combatant) {
            Combatant selectable = (Combatant)item;
            if (selectable.getSelectable() && !selectable.getSelected()) {
                selectable.setSelected(true);
                events.add(new SelectEvent(selectable, true));
            }
        }
    }
}
