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
 * Implementors of this {@link Event} specialization represent an {@code Event}
 * with both a subject and a recipient.
 *
 * @author Blair Butterworth
 */
public interface RecipientEvent extends Event
{
    /**
     * The {@link GameObject} receiving the result of a given operation.
     */
    GameObject getRecipient();
}
