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
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.spatial.GameObjectNode;

import javax.inject.Inject;

/**
 * A helper class for generating move events.
 *
 * @author Blair Butterworth
 */
public class MoveEvents
{
    private Events events;

    @Inject
    public MoveEvents(Events events) {
        this.events = events;
    }

    public void move(GameObject subject) {
        events.add(new MoveEvent(subject, MoveStatus.Updated));
        events.add(new MoveEvent(subject, MoveStatus.Complete));
    }

    public void moveCancelled(GameObject subject) {
        events.add(new MoveEvent(subject, MoveStatus.Cancelled));
    }

    public void moveComplete(GameObject subject) {
        events.add(new MoveEvent(subject, MoveStatus.Complete));
    }

    public void moveFailed(GameObject subject) {
        events.add(new MoveEvent(subject, MoveStatus.Failed));
    }

    public void moveUpdate(GameObject subject, GameObjectNode node) {
        events.add(new MoveEvent(subject, node, MoveStatus.Updated));
    }
}
