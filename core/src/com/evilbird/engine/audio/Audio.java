/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.audio;

import com.badlogic.gdx.utils.Disposable;

/**
 * Implementors of this interface represent an audio stream.
 *
 * @author Blair Butterworth
 */
public interface Audio extends Disposable
{
    /**
     * Returns whether or not the sound is currently playing.
     */
    boolean isPlaying();

    /**
     * Plays the sound. If the sound is already playing, then resulting
     * behaviour is undefined. {@code Music} implementations will be restarted,
     * whilst {@code Sound} will play a new instance in parallel.
     */
    void play();

    /**
     * Stops playing the sound. If the sound is no longer playing this has no
     * effect.
     */
    void stop();

    /**
     * Sets the volume of the playing sound.
     *
     * @param volume the volume in the range 0 (silent) to 1 (max volume).
     */
    void setVolume(float volume);
}