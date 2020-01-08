/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;

import java.util.Objects;

/**
 * Represents a sound effect, a short audio clip usually played in response to
 * an event in the game.
 *
 * @author Blair Butterworth
 */
public abstract class AbstractSound implements Sound
{
    protected static final int UNINITIALIZED = -1;

    protected long identifier;
    protected com.badlogic.gdx.audio.Sound delegate;

    /**
     * Constructs a new instance of this class, given the LibGdx {@code Sound}
     * that will be played when this class is used.
     *
     * @param sound a LibGdx {@code Sound}.
     *
     * @throws NullPointerException if the given {@code Sound} is {@code null}.
     */
    public AbstractSound(com.badlogic.gdx.audio.Sound sound) {
        Objects.requireNonNull(sound);
        this.delegate = sound;
        this.identifier = UNINITIALIZED;
    }

    public AbstractSound(FileHandleResolver resolver, String path) {
        this(Gdx.audio.newSound(resolver.resolve(path)));
    }

    @Override
    public void dispose() {
        if (identifier != UNINITIALIZED){
            delegate.dispose();
        }
    }

    @Override
    public void play() {
        identifier = delegate.play();
    }

    @Override
    public void stop() {
        if (identifier != UNINITIALIZED){
            delegate.stop(identifier);
            identifier = UNINITIALIZED;
        }
    }

    @Override
    public void setVolume(float volume) {
        if (identifier != UNINITIALIZED){
            delegate.setVolume(identifier, volume);
        }
    }
}
