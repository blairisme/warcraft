/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.audio.music;

import com.evilbird.engine.audio.Audio;

/**
 * Represents a streamed audio file.
 *
 * @author Blair Butterworth
 */
public interface Music extends Audio
{
    /**
     * Adds a {@link MusicObserver} instance to the collection of observers
     * that will be notified when a music instance is played, stopped or
     * completes.
     */
    void addObserver(MusicObserver observer);

    /**
     * Removes a {@link MusicObserver} instance from the collection of observers
     * that are notified when a music instance is played, stopped or
     * completes.
     */
    void removeObserver(MusicObserver observer);
}
