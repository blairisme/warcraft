/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio;

/**
 * Instances of this class represent a {@link SoundEffect} that doesn't produce
 * an audio output, I.E., its silent.
 *
 * @author Blair Butterworth
 */
public class SilentSoundEffect implements SoundEffect
{
    @Override
    public void play() {
    }

    @Override
    public void stop() {
    }
}
