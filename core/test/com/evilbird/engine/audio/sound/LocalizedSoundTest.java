/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.sound;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.object.GameObject;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Instances of this unit test validate the {@link LocalizedSound} class.
 *
 * @author Blair Butterworth
 */
public class LocalizedSoundTest
{
    private Sound delegate;
    private GameObject owner;
    private LocalizedSoundExtension sound;

    @Before
    public void setup() {
        delegate = mock(Sound.class);
        owner = mock(GameObject.class);
        sound = new LocalizedSoundExtension(delegate, owner);
    }

    @Test(expected=NullPointerException.class)
    public void constructNullSoundTest() {
        new LocalizedSound(null, owner);
    }

    @Test(expected=NullPointerException.class)
    public void constructOwnerSoundTest() {
        new LocalizedSound(delegate, null);
    }

    @Test
    public void playTest() {
        sound.play();
        verify(delegate).play();
    }

    @Test
    public void stopTest() {
        sound.stop();
        verify(delegate).stop();
    }

    @Test
    public void updateOnScreenTest() {
        sound.onCamera = true;
        sound.update();
        verify(delegate).setVolume(1f);
    }

    @Test
    public void updateOffScreenTest() {
        sound.onCamera = false;
        sound.update();
        verify(delegate).setVolume(0f);
    }

    /**
     * Avoids native method calls, and subsequent UnsatisfiedLinkError
     * exceptions caused by updates to or use of the cameras Frustum,
     * a final field that can't be mocked.
     */
    private static class LocalizedSoundExtension extends LocalizedSound {
        private boolean onCamera;

        public LocalizedSoundExtension(Sound sound, GameObject owner) {
            super(sound, owner);
        }

        @Override
        protected boolean onCamera(Vector2 position) {
            return onCamera;
        }
    }
}