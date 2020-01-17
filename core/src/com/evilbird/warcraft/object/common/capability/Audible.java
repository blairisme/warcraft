/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.capability;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Implementors of this interface represent a object which produces sound.
 * Methods are provided to obtain the currently playing sound and to and to
 * specify that another be used.
 *
 * @author Blair Butterworth
 */
public interface Audible
{
    /**
     * Returns the identifier of the currently playing sound effect.
     *
     * @return  an {@link Identifier}. This methods may return {@code null},
     *          indicating that no sound is currently being played.
     */
    Identifier getSound();

    /**
     * Returns whether or not the audible object supports the given sound.
     */
    boolean hasSound(Identifier id);

    /**
     * Sets the currently playing sound, identified by the given sound
     * identifier.
     *
     * @param id        an {@link Identifier}. This methods may be {@code null}
     *                  indicating that no sound should be used.
     */
    void setSound(Identifier id);
}
