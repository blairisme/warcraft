/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
