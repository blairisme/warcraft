/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
