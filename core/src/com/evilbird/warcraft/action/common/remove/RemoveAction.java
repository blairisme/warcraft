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
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.events.Events;
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
    private Events events;
    private Supplier<Item> supplier;

    public RemoveAction(ActionRecipient target, Events events) {
        this.supplier = new ActionTargetSupplier(target);
        this.events = events;
    }

    public RemoveAction(Supplier<Item> supplier, Events events) {
        this.supplier = supplier;
        this.events = events;
    }

    public static RemoveAction remove(Events events) {
        return new RemoveAction(ActionRecipient.Subject, events);
    }

    public static RemoveAction remove(ActionRecipient target) {
        return new RemoveAction(target, observer -> {});
    }

    public static RemoveAction remove(ActionRecipient target, Events events) {
        return new RemoveAction(target, events);
    }

    public static RemoveAction remove(Supplier<Item> supplier, Events events) {
        return new RemoveAction(supplier, events);
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
        if (events != null) {
            events.add(new RemoveEvent(item));
        }
    }

    private class ActionTargetSupplier implements Supplier<Item> {
        private ActionRecipient source;

        public ActionTargetSupplier(ActionRecipient source) {
            this.source = source;
        }

        @Override
        public Item get() {
            switch (source) {
                case Subject: return getItem();
                case Target: return getTarget();
                default: throw new UnsupportedOperationException();
            }
        }
    }
}
