/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.remove.RemoveTarget;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.action.placeholder.PlaceholderEvents.notifyPlaceholderRemoved;

/**
 * Instances of this class stop the use of a placeholder, removing it from the
 * game.
 *
 * @author Blair Butterworth
 */
public class PlaceholderCancel extends RemoveTarget
{
    @Inject
    public PlaceholderCancel(EventQueue events) {
        super(events);
        setIdentifier(PlaceholderActions.PlaceholderCancel);
    }

    @Override
    public boolean act(float time) {
        Unit builder = (Unit)getItem();
        Item building =  builder.getAssociatedItem();
        return cancel(builder, building, time);
    }

    private boolean cancel(Unit builder, Item building, float time) {
        setTarget(building);
        if (super.act(time)) {
            builder.setAssociatedItem(null);
            notifyPlaceholderRemoved(events, builder, building);
            return ActionComplete;
        }
        return ActionIncomplete;
    }
}
