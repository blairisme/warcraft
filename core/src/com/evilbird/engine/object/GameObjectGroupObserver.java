/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object;

/**
 * Implementors of this interface provide methods that are called when
 * {@link GameObject GameObjects} are added or removed from a
 * {@link GameObjectGroup}.
 *
 * @author Blair Butterworth
 */
public interface GameObjectGroupObserver
{
    void objectAdded(GameObject gameObject);

    void objectRemoved(GameObject gameObject);

    void objectsCleared();
}
