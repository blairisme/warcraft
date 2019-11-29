/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.sound;

import com.badlogic.gdx.audio.Sound;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Instances of this unit test validate the {@link BasicSound} class.
 *
 * @author Blair Butterworth
 */
public class BasicSoundTest
{
    private Sound sound;
    private BasicSound effect;

    @Before
    public void setup() {
        sound = Mockito.mock(Sound.class);
        effect = new BasicSound(sound);
    }

    @Test(expected=NullPointerException.class)
    public void constructNullTest() {
        new BasicSound(null);
    }

    @Test
    public void playTest() {
        effect.play();
        verify(sound).play();

        effect.play();
        verify(sound, times(2)).play();
    }

    @Test
    public void stopTest() {
        effect.stop();
        verify(sound, never()).stop(anyLong());

        effect.play();

        effect.stop();
        verify(sound, times(1)).stop(anyLong());

        effect.stop();
        verify(sound, times(1)).stop(anyLong());
    }

    @Test
    public void pauseTest() {
        effect.pause();
        verify(sound, never()).pause(anyLong());

        effect.play();
        effect.pause();
        verify(sound, times(1)).pause(anyLong());

        effect.resume();
        effect.pause();
        verify(sound, times(2)).pause(anyLong());

        effect.stop();
        effect.pause();
        verify(sound, times(2)).pause(anyLong());
    }

    @Test
    public void resumeTest() {
        effect.resume();
        verify(sound, never()).resume(anyLong());

        effect.play();
        effect.resume();
        verify(sound, times(1)).resume(anyLong());

        effect.pause();
        effect.resume();
        verify(sound, times(2)).resume(anyLong());

        effect.stop();
        effect.resume();
        verify(sound, times(2)).resume(anyLong());
    }

    @Test
    public void setVolumeTest() {
        effect.setVolume(1f);
        verify(sound, never()).setVolume(anyLong(), anyFloat());

        effect.play();
        effect.setVolume(1f);
        verify(sound, times(1)).setVolume(anyLong(), anyFloat());

        effect.stop();
        effect.setVolume(1);
        verify(sound, times(1)).setVolume(anyLong(), anyFloat());
    }
}