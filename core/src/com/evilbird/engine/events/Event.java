/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.events;

import com.evilbird.engine.object.GameObject;

/**
 * Implementors of this interface represent an instance of a note worthy
 * phenomenon: an event.
 *
 * @author Blair Butterworth
 */
public interface Event
{
    /**
     * The {@link GameObject} involved in the event.
     */
    GameObject getSubject();

    /**
     * Determines if the event has been assigned the given subject.
     */
    default boolean hasSubject(GameObject subject) {
        return subject == getSubject();
    }
}
