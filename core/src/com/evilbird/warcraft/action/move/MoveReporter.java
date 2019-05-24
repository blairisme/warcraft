/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.spatial.ItemNode;

import javax.inject.Inject;

/**
 * Instances of this class observe movement events and report them to the
 * {@link EventQueue} stream.
 *
 * @author Blair Butterworth
 */
class MoveReporter implements MoveObserver
{
    private EventQueue events;

    @Inject
    public MoveReporter(EventQueue events) {
        this.events = events;
    }

    @Override
    public void onMove(Item subject, ItemNode location) {
        events.add(new MoveEvent(subject, location, MoveStatus.Updated));
    }

    @Override
    public void onMoveComplete(Item subject) {
        events.add(new MoveEvent(subject, null, MoveStatus.Complete));
    }

    @Override
    public void onMoveFailed(Item subject) {
        events.add(new MoveEvent(subject, null, MoveStatus.Failed));
    }

    @Override
    public void onMoveCancelled(Item subject) {
        events.add(new MoveEvent(subject, null, MoveStatus.Cancelled));
    }
}
