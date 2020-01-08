/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.graphics.animation;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.lang.Identifier;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of uniquely identified {@link Animation Animations}.
 *
 * @author Blair Butterworth
 */
public abstract class AnimationCatalog
{
    private Map<Identifier, Identifier> aliases;
    private Map<Identifier, Animation> animations;
    private Map<Identifier, AnimationCatalogProvider> definitions;

    /**
     * Creates a new instance of this class given the catalogs initial
     * capacity. If more animations are added than this capacity allows, then
     * the catalog will be resized to accommodate the additional animations.
     *
     * @param capacity the initial capacity of the catalog.
     *
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public AnimationCatalog(int capacity) {
        animations = new HashMap<>(capacity);
        definitions = new HashMap<>(capacity);
        aliases = new HashMap<>(capacity);
    }

    /**
     * Returns all of the animations contained in the catalog.
     *
     * @return a {@link Map} of animations and their unique identifiers.
     */
    public Map<Identifier, Animation> get() {
        Maps.forEach(definitions, this::set);
        Maps.forEach(aliases, this::set);
        return Maps.convert(animations, Animation::copy);
    }

    protected AnimationCatalogEntry animation(Identifier identifier) {
        AnimationCatalogEntry definition = new AnimationCatalogEntry();
        definitions.put(identifier, definition);
        return definition;
    }

    protected AnimationCatalogSequence sequence(Identifier identifier) {
        AnimationCatalogSequence sequence = new AnimationCatalogSequence();
        definitions.put(identifier, sequence);
        return sequence;
    }

    protected void alias(Identifier alias, Identifier animation) {
        aliases.put(alias, animation);
    }

    protected void set(Identifier alias, Identifier animation) {
        Animation target = animations.get(animation);
        animations.put(alias, target);
    }

    protected void set(Identifier id, AnimationCatalogProvider definition) {
        set(id, definition.getAnimation());
    }

    protected void set(Identifier id, Animation animation) {
        animations.put(id, animation);
    }
}
