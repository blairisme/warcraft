/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.sound;

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
    public void stop() {
    }

    @Override
    public void setVolume(float volume) {
    }
}
