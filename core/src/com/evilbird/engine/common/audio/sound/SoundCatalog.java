/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.sound;

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
