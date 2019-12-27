/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.audio.sound;

import com.badlogic.gdx.audio.Sound;

/**
 * @author Blair Butterworth
 */
public class TestSound extends AbstractSound
{
    private boolean playing;

    public TestSound(Sound sound) {
        super(sound);
        playing = false;
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public void play() {
        super.play();
        playing = true;
    }

    @Override
    public void stop() {
        super.stop();
        playing = false;
    }
}
