/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;

import java.util.Objects;

/**
 * An {@link Action} that sets an {@link Item Items} selected state.
 *
 * @author Blair Butterworth
 */
public class SelectAction extends BasicAction
{
    private boolean selected;
    private SelectObserver observer;

    public SelectAction(boolean selected, SelectObserver observer) {
        Objects.requireNonNull(observer);
        this.selected = selected;
        this.observer = observer;
    }

    public static SelectAction select(SelectObserver observer) {
        return new SelectAction(true, observer);
    }

    public static SelectAction deselect(SelectObserver observer) {
        return new SelectAction(false, observer);
    }

    @Override
    public boolean act(float time) {
        Item item = getItem();
        item.setSelected(selected);
        observer.onSelect(item, selected);
        return true;
    }
}
