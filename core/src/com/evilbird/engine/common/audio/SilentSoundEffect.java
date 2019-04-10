/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio;

/**
 * Instances of this class represent a {@link SoundEffect} that doesnt startProducing
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
