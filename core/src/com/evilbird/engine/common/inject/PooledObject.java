/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
