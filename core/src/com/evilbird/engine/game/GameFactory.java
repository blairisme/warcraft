/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Identifier;

/**
 * Implementors of interface represent an object factory.
 *
 * @param <T> the class of objects created by the factory.
 *
 * @author Blair Butterworth
 */
public interface GameFactory <T>
{
    /**
     * Creates a new object of the given {@link Identifier type}.
     *
     * @param type  the type of the desired object.
     * @return      a new object. This method will not return {@code null}.
     *
     * @throws UnknownEntityException   thrown if a menu with the given
     *                                  identifier doesn't exist.
     */
    T get(Identifier type) throws UnknownEntityException;

    /**
     * Instructs the factory to load any persisted assets specific to the given
     * context and use these when creating new objects.
     *
     * @param context   a context identifier.
     */
    void load(Identifier context);

    /**
     * Instructs the factory to unload any previously loaded persisted assets
     * pertaining to the given context.
     *
     * @param context   a context identifier.
     */
    void unload(Identifier context);
}
