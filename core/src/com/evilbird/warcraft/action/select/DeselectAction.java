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
    private SelectObserver observer;
    private Predicate<Item> condition;

    public DeselectAction(Predicate<Item> condition, SelectObserver observer) {
        this.observer = observer;
        this.condition = condition;
    }

    public static DeselectAction deselectAll(SelectObserver observer) {
        return deselectAll(isSelected(), observer);
    }

    public static DeselectAction deselectAll(Predicate<Item> condition, SelectObserver observer) {
        return new DeselectAction(both(isSelected(), condition), observer);
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
            observer.onSelect(selectable, false);
        }
    }
}
