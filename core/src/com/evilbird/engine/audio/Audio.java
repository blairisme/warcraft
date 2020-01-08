/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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