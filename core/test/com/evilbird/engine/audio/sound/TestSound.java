/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
