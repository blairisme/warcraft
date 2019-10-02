/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

public class MoveEvents
{
    private Events events;

    @Inject
    public MoveEvents(Events events) {
        this.events = events;
    }

    public void notifyMove(Item subject) {
        notifyMoveUpdated(subject);
        notifyMoveComplete(subject);
    }

    public void notifyMoveCancelled(Item subject) {
        events.add(new MoveEvent(subject, MoveStatus.Cancelled));
    }

    public void notifyMoveComplete(Item subject) {
        events.add(new MoveEvent(subject, MoveStatus.Complete));
    }

    public void notifyMoveFailed(Item subject) {
        events.add(new MoveEvent(subject, MoveStatus.Failed));
    }

    public void notifyMoveUpdated(Item subject) {
        events.add(new MoveEvent(subject, MoveStatus.Updated));
    }
}
