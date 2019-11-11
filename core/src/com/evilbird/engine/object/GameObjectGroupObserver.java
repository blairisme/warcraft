/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
