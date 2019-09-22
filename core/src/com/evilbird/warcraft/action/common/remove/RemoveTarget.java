/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.remove;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.warcraft.action.common.remove.RemoveEvents.notifyRemove;

/**
 * Instances of this {@link Action} remove a given {@link Item} when invoked.
 *
 * @author Blair Butterworth
 */
public class RemoveTarget extends BasicAction
{
    protected Events events;

    @Inject
    public RemoveTarget(Events events) {
        this.events = events;
    }

    @Override
    public boolean act(float delta) {
        Item item = getTarget();
        removeItem(item);
        notifyRemove(events, item);
        return ActionComplete;
    }

    protected void removeItem(Item item) {
        ItemGroup parent = item.getParent();
        parent.removeItem(item);
    }
}
