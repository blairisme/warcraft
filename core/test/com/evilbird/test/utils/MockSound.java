/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.utils;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.evilbird.engine.audio.sound.AbstractSound;
import com.evilbird.engine.audio.sound.Sound;

/**
 * A {@link Sound} implementation for use in unit tests.
 *
 * @author Blair Butterworth
 */
public class MockSound extends AbstractSound
{
    private boolean playing;

    public MockSound(FileHandleResolver resolver, String path) {
        super(resolver, path);
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
