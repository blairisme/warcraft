/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
    void load(GameContext context);

    /**
     * Instructs the factory to unload any previously loaded persisted assets
     * pertaining to the given context.
     *
     * @param context   a context identifier.
     */
    void unload(GameContext context);
}
