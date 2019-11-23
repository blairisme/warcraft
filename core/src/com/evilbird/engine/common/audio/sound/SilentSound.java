/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.sound;

/**
 * Instances of this class represent a {@link Sound} that doesn't produce
 * an audio output, I.E., its silent.
 *
 * @author Blair Butterworth
 */
public class SilentSound implements Sound
{
    public static final SilentSound SilentSoundEffect = new SilentSound();

    @Override
    public void play() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void setVolume(float volume) {
    }
}
