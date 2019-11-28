/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.create;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;

import javax.inject.Inject;

/**
 * Helper class for generating create events.
 *
 * @author Blair Butterworth
 */
public class CreateEvents
{
    private Events events;

    @Inject
    public CreateEvents(Events events) {
        this.events = events;
    }

    public void notifyCreate(GameObject gameObject) {
        events.add(new CreateEvent(gameObject));
    }
}
