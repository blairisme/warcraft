/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio;

import com.badlogic.gdx.audio.Sound;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Instances of this unit test validate the {@link BasicSoundEffect} class.
 *
 * @author Blair Butterworth
 */
public class BasicSoundEffectTest
{
    private Sound sound;
    private String path;
    private BasicSoundEffect effect;

    @Before
    public void setup() {
        sound = Mockito.mock(Sound.class);
        path = "/data/sounds/sound1.mp3";
        effect = new BasicSoundEffect(sound, path);
    }

    @Test
    public void playTest() {
        effect.play();
        verify(sound).play();
    }

    @Test
    public void stopTest() {
        effect.stop();
        verify(sound, never()).stop(anyLong());

        effect.play();

        effect.stop();
        verify(sound, times(1)).stop(anyLong());
    }

    @Test
    public void getPathTest() {
        assertEquals(path, effect.getPath());
    }
}