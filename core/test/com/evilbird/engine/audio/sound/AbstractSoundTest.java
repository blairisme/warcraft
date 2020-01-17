/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.sound;

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
 * Instances of this unit test validate the {@link AbstractSound} class.
 *
 * @author Blair Butterworth
 */
public class AbstractSoundTest
{
    private Sound sound;
    private AbstractSound effect;

    @Before
    public void setup() {
        sound = Mockito.mock(Sound.class);
        effect = new TestSound(sound);
    }

    @Test(expected=NullPointerException.class)
    public void constructNullTest() {
        new TestSound(null);
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
    public void setVolumeTest() {
        effect.setVolume(1f);
        verify(sound, never()).setVolume(anyLong(), anyFloat());

        effect.play();
        verify(sound, times(1)).setVolume(anyLong(), anyFloat());

        effect.stop();
        effect.setVolume(1);
        verify(sound, times(1)).setVolume(anyLong(), anyFloat());
    }
}