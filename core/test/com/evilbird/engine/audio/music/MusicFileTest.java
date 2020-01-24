/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.music;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link MusicFile} class.
 *
 * @author Blair Butterworth
 */
public class MusicFileTest
{
    private String path;
    private Audio service;
    private FileHandle handle;
    private FileHandleResolver resolver;
    private Music music;
    private MusicFile lazyMusic;

    @Before
    public void setup() {
        path = "/data/music/music1.mp3";
        resolver = mock(FileHandleResolver.class);
        service = mock(Audio.class);
        lazyMusic = new MusicFile(service, resolver, path);
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
    public void stopTest() {
        lazyMusic.play();
        when(music.isPlaying()).thenReturn(true);

        lazyMusic.stop();
        verify(music, times(1)).stop();
    }

    @Test
    public void isPlayingTest() {
        verify(music, times(0)).isPlaying();
        lazyMusic.play();
        reset(music);

        lazyMusic.isPlaying();
        verify(music, times(1)).isPlaying();
    }

    @Test
    public void disposeTest() {
        lazyMusic.dispose();
        verify(music, never()).dispose();

        lazyMusic.play();
        lazyMusic.dispose();
        verify(music, times(1)).dispose();
    }
}