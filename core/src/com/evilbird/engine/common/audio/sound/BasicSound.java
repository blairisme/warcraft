/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.sound;

import java.util.Objects;

/**
 * Represents a sound effect, a short audio clip usually played in response to
 * an event in the game.
 *
 * @author Blair Butterworth
 */
public class BasicSound implements Sound
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
    public BasicSound(com.badlogic.gdx.audio.Sound sound) {
        Objects.requireNonNull(sound);
        this.delegate = sound;
        this.identifier = UNINITIALIZED;
    }

    @Override
    public void play() {
        //stop();
        identifier = delegate.play();
    }

    @Override
    public void pause() {
        if (identifier != UNINITIALIZED){
            delegate.pause(identifier);
        }
    }

    @Override
    public void resume() {
        if (identifier != UNINITIALIZED){
            delegate.resume(identifier);
        }
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
