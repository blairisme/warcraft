/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selector;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.selector.SelectorStatus.Added;
import static com.evilbird.warcraft.action.selector.SelectorStatus.Removed;

/**
 * Helper class for generating selector events.
 *
 * @author Blair Butterworth
 */
public class SelectorEvents
{
    private Events events;

    @Inject
    public SelectorEvents(Events events) {
        this.events = events;
    }

    public void notifySelectorAdded(GameObject builder, GameObject selector) {
        events.add(new SelectorEvent(builder, selector, Added));
    }

    public void notifySelectorRemoved(GameObject builder, GameObject selector) {
        events.add(new SelectorEvent(builder, selector, Removed));
    }
}
