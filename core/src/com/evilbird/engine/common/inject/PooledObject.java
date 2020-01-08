/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.inject;

import com.badlogic.gdx.utils.Pool;

/**
 * Represents an object stored in a {@link Pool}.
 *
 * @author Blair Butterworth
 */
public interface PooledObject<T> extends Pool.Poolable
{
    /**
     * Returns the pool the object belongs to.
     */
    Pool<T> getPool();

    /**
     * Sets the pool the object belongs to.
     */
    void setPool(Pool<T> pool);
}
