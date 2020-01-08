/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.music;

/**
 * Implementors of this interface provide methods that will be called when
 * {@link Music} instances are played, stopped and completed.
 *
 * @author Blair Butterworth
 */
public interface MusicObserver
{
    /**
     * Called when the given {@link Music} stream is played.
     */
    void onStart(Music music);

    /**
     * Called when the given {@link Music} stream is stopped, before it finishes.
     */
    void onStop(Music music);

    /**
     * Called when the given {@link Music} stream is complete.
     */
    void onComplete(Music music);
}
