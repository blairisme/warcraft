/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.sound;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.Item;
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
    private Item owner;
    private LocalizedSoundExtension sound;

    @Before
    public void setup() {
        delegate = mock(Sound.class);
        owner = mock(Item.class);
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
    public void pauseTest() {
        sound.pause();
        verify(delegate).pause();
    }

    @Test
    public void resumeTest() {
        sound.resume();
        verify(delegate).resume();
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

        public LocalizedSoundExtension(Sound sound, Item owner) {
            super(sound, owner);
        }

        @Override
        protected boolean onCamera(Vector2 position) {
            return onCamera;
        }
    }
}