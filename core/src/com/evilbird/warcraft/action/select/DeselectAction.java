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

    public DeselectAction(SelectObserver observer) {
        this.observer = observer;
    }

    public static DeselectAction deselectAll(SelectObserver observer) {
        return new DeselectAction(observer);
    }

    @Override
    public boolean act(float delta) {
        Item item = getItem();
        ItemRoot root = item.getRoot();
        Collection<Item> items = root.findAll(isSelected());
        Collection<Selectable> selected = items.stream().map(element -> (Selectable) element).collect(toList());
        selected.forEach(this::deselect);
        return true;
    }

    private void deselect(Selectable selectable) {
        selectable.setSelected(false);
        observer.onSelect(selectable, false);
    }
}
