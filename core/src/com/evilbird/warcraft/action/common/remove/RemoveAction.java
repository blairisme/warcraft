/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.remove;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;

import java.util.function.Supplier;

/**
 * Instances of this {@link Action} remove a given {@link Item} when invoked.
 *
 * @author Blair Butterworth
 */
public class RemoveAction extends BasicAction
{
    private Supplier<Item> supplier;
    private RemoveObserver observer;

    public RemoveAction(ActionTarget target, RemoveObserver observer) {
        this.supplier = new ActionTargetSupplier(target);
        this.observer = observer;
    }

    public RemoveAction(Supplier<Item> supplier, RemoveObserver observer) {
        this.supplier = supplier;
        this.observer = observer;
    }

    public static RemoveAction remove(RemoveObserver observer) {
        return new RemoveAction(ActionTarget.Item, observer);
    }

    public static RemoveAction remove(ActionTarget target, RemoveObserver observer) {
        return new RemoveAction(target, observer);
    }

    public static RemoveAction remove(Supplier<Item> supplier, RemoveObserver observer) {
        return new RemoveAction(supplier, observer);
    }

    @Override
    public boolean act(float delta) {
        Item item = supplier.get();
        removeItem(item);
        notifyRemove(item);
        setTarget(null);
        return true;
    }

    protected void removeItem(Item item) {
        ItemGroup parent = item.getParent();
        parent.removeItem(item);
    }

    protected void notifyRemove(Item item) {
        if (observer != null) {
            observer.onRemove(item);
        }
    }

    private class ActionTargetSupplier implements Supplier<Item> {
        private ActionTarget source;

        public ActionTargetSupplier(ActionTarget source) {
            this.source = source;
        }

        @Override
        public Item get() {
            switch (source) {
                case Item: return getItem();
                case Target: return getTarget();
                default: throw new UnsupportedOperationException();
            }
        }
    }
}
