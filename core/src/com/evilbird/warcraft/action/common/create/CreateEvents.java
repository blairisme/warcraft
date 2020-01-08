/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
