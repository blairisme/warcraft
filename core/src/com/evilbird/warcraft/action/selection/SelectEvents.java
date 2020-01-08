/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selection;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;

import javax.inject.Inject;

/**
 * Helper class for generating selection events.
 *
 * @author Blair Butterworth
 */
public class SelectEvents
{
    private Events events;

    @Inject
    public SelectEvents(Events events) {
        this.events = events;
    }

    public void selectionUpdated(GameObject gameObject, boolean selected) {
        events.add(new SelectEvent(gameObject, selected));
    }
}
