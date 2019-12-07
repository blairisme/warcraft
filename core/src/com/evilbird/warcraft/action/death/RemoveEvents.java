/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.death;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;

import javax.inject.Inject;

/**
 * Helper class for generating removal events.
 *
 * @author Blair Butterworth
 */
public class RemoveEvents
{
    private Events events;

    @Inject
    public RemoveEvents(Events events) {
        this.events = events;
    }

    public void objectRemoved(GameObject gameObject) {
        events.add(new RemoveEvent(gameObject));
    }
}
