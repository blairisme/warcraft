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
 * A {@link MusicObserver} implementation that doesn't respond to music events.
 *
 * @author Blair Butterworth
 */
public class MusicObserverAdapter implements MusicObserver
{
    @Override
    public void onStart(Music music) {
    }

    @Override
    public void onStop(Music music) {
    }

    @Override
    public void onComplete(Music music) {
    }
}
