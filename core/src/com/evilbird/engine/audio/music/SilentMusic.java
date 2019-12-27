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
 * Instances of this class represent a {@link Music} that doesn't produce
 * an audio output, I.E., its silent.
 *
 * @author Blair Butterworth
 */
public class SilentMusic implements Music
{
    public static final SilentMusic SilentMusicFile = new SilentMusic();

    @Override
    public void addObserver(MusicObserver observer) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public void play() {
    }

    @Override
    public void removeObserver(MusicObserver observer) {
    }

    @Override
    public void stop() {
    }

    @Override
    public void setVolume(float volume) {
    }
}
