/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
        events.addDelayed(new MoveEvent(subject, MoveStatus.Complete));
    }

    public void moveCancelled(GameObject subject) {
        events.addDelayed(new MoveEvent(subject, MoveStatus.Cancelled));
    }

    public void moveComplete(GameObject subject) {
        events.addDelayed(new MoveEvent(subject, MoveStatus.Complete));
    }

    public void moveFailed(GameObject subject) {
        events.addDelayed(new MoveEvent(subject, MoveStatus.Failed));
    }

    public void moveUpdate(GameObject subject, GameObjectNode node) {
        events.add(new MoveEvent(subject, node, MoveStatus.Updated));
    }
}
