/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
