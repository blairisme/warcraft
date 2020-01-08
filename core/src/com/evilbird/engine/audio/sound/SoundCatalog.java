/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.sound;

import com.evilbird.engine.common.lang.Identifier;

import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class represent a collection of uniquely identified
 * {@link Sound sounds}.
 *
 * @author Blair Butterworth
 */
public abstract class SoundCatalog
{
    private Map<Identifier, Sound> sounds;

    /**
     * Creates a new instance of this class given the catalogs initial
     * capacity. If more {@link Sound sounds} are added than this capacity
     * allows, then the catalog will be resized to accommodate the additional
     * {@code Sounds}.
     *
     * @param capacity the initial capacity of the catalog.
     *
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public SoundCatalog(int capacity) {
        sounds = new HashMap<>(capacity);
    }

    /**
     * Returns all of the {@link Sound sounds} contained in the catalog.
     *
     * @return a {@link Map} of {@code Sounds} and their unique identifiers.
     */
    public Map<Identifier, Sound> get() {
        return sounds;
    }

    protected void sound(Identifier id, Sound sound) {
        sounds.put(id, sound);
    }
}
