/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Selectable;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;

import java.util.Collection;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isSelected;
import static java.util.stream.Collectors.toList;

/**
 * Instances of this {@link Action} deselect all currently selected items.
 *
 * @author Blair Butterworth
 */
public class DeselectAction extends BasicAction
{
    private Events events;
    private Predicate<Item> condition;

    public DeselectAction(Predicate<Item> condition, Events events) {
        this.events = events;
        this.condition = condition;
    }

    public static DeselectAction deselectAll(Events events) {
        return deselectAll(isSelected(), events);
    }

    public static DeselectAction deselectAll(Predicate<Item> condition, Events events) {
        return new DeselectAction(both(isSelected(), condition), events);
    }

    @Override
    public boolean act(float delta) {
        Item item = getItem();
        ItemRoot root = item.getRoot();
        Collection<Item> items = root.findAll(condition);
        Collection<Selectable> selected = items.stream().map(element -> (Selectable) element).collect(toList());
        selected.forEach(this::deselect);
        return true;
    }

    private void deselect(Selectable selectable) {
        if (selectable.getSelected()) {
            selectable.setSelected(false);
            events.add(new SelectEvent(selectable, false));
        }
    }
}
