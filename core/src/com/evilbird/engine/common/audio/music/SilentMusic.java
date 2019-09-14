/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.music;

import com.badlogic.gdx.audio.Music;

/**
 * Instances of this class represent a {@link Music} that doesn't produce
 * an audio output, I.E., its silent.
 *
 * @author Blair Butterworth
 */
public class SilentMusic implements Music
{
    @Override
    public void play() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void stop() {
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public void setLooping(boolean isLooping) {
    }

    @Override
    public boolean isLooping() {
        return false;
    }

    @Override
    public void setVolume(float volume) {
    }

    @Override
    public float getVolume() {
        return 0;
    }

    @Override
    public void setPan(float pan, float volume) {
    }

    @Override
    public void setPosition(float position) {
    }

    @Override
    public float getPosition() {
        return 0;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void setOnCompletionListener(OnCompletionListener listener) {
    }
}
