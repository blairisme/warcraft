/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.music;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.files.FileHandle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link LazyLoadedMusic} class.
 *
 * @author Blair Butterworth
 */
public class LazyLoadedMusicTest
{
    private String path;
    private Audio service;
    private FileHandle handle;
    private FileHandleResolver resolver;
    private Music music;
    private LazyLoadedMusic lazyMusic;

    @Before
    public void setup() {
        path = "/data/music/music1.mp3";
        resolver = mock(FileHandleResolver.class);
        service = mock(Audio.class);
        lazyMusic = new LazyLoadedMusic(service, resolver, path);
        music = mock(Music.class);
        handle = mock(FileHandle.class);
        when(resolver.resolve(path)).thenReturn(handle);
        when(service.newMusic(any(FileHandle.class))).thenReturn(music);
    }

    @Test
    public void playTest() {
        lazyMusic.play();
        verify(service, times(1)).newMusic(any(FileHandle.class));
        verify(music, times(1)).play();

        reset(service, music);
        lazyMusic.play();

        verify(service, never()).newMusic(any(FileHandle.class));
        verify(music, times(1)).play();
    }

    @Test
    public void pauseTest() {
        lazyMusic.pause();
        verify(music, never()).pause();

        lazyMusic.play();
        lazyMusic.pause();
        verify(music, times(1)).pause();
    }

    @Test
    public void stopTest() {
        lazyMusic.stop();
        verify(music, never()).stop();

        lazyMusic.play();
        lazyMusic.stop();
        verify(music, times(1)).stop();
    }

    @Test
    public void isPlayingTest() {
        assertFalse(lazyMusic.isPlaying());
        verify(music, never()).isPlaying();

        lazyMusic.play();
        lazyMusic.isPlaying();
        verify(music, times(1)).isPlaying();
    }

    @Test
    public void loopingTest() {
        lazyMusic.setLooping(true);
        verify(music, never()).setLooping(true);

        assertTrue(lazyMusic.isLooping());

        lazyMusic.play();
        verify(music, times(1)).setLooping(true);
    }

    @Test
    public void volumeTest() {
        lazyMusic.setVolume(0.5f);
        verify(music, never()).setVolume(0.5f);

        assertEquals(lazyMusic.getVolume(), 0.5f, 0.01f);

        lazyMusic.play();
        verify(music, times(1)).setVolume(0.5f);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void setPanTest() {
        lazyMusic.setPan(1, 1);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void setPositionTest() {
        lazyMusic.setPosition(1);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void getPositionTest() {
        lazyMusic.getPosition();
    }

    @Test
    public void disposeTest() {
        lazyMusic.dispose();
        verify(music, never()).dispose();

        lazyMusic.play();
        lazyMusic.dispose();
        verify(music, times(1)).dispose();
    }

    @Test
    public void setOnCompletionListenerTest() {
        OnCompletionListener listener = mock(OnCompletionListener.class);

        lazyMusic.setOnCompletionListener(listener);
        verify(music, never()).setOnCompletionListener(listener);

        lazyMusic.play();
        verify(music, times(1)).setOnCompletionListener(listener);
    }
}