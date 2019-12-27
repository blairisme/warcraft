/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
