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
import com.evilbird.engine.action.common.ActionTarget;
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
    private ActionTarget source;
    private SelectObserver observer;

    public SelectAction(ActionTarget source, boolean selected, SelectObserver observer) {
        Objects.requireNonNull(observer);
        this.source = source;
        this.selected = selected;
        this.observer = observer;
    }

    public static SelectAction select(SelectObserver observer) {
        return new SelectAction(ActionTarget.Item, true, observer);
    }

    public static SelectAction select(ActionTarget source, SelectObserver observer) {
        return new SelectAction(source, true, observer);
    }

    public static SelectAction deselect(SelectObserver observer) {
        return new SelectAction(ActionTarget.Item, false, observer);
    }

    public static SelectAction deselect(ActionTarget source, SelectObserver observer) {
        return new SelectAction(source, false, observer);
    }

    @Override
    public boolean act(float time) {
        Item item = getSelectable();
        item.setSelected(selected);
        observer.onSelect(item, selected);
        return true;
    }

    private Item getSelectable() {
        switch (source) {
            case Item: return getItem();
            case Target: return getTarget();
            default: throw new UnsupportedOperationException();
        }
    }
}
