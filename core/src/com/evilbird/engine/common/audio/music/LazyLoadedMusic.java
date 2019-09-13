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
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

/**
 * Represents a streamed audio file that's loaded at the point its played.
 *
 * @author Blair Butterworth
 */
public class LazyLoadedMusic implements Music
{
    private Music music;
    private String path;
    private Audio service;
    private FileHandleResolver resolver;

    private float volume;
    private boolean looping;
    private OnCompletionListener listener;

    public LazyLoadedMusic(FileHandleResolver resolver, String path) {
        this(Gdx.audio, resolver, path);
    }

    public LazyLoadedMusic(Audio service, FileHandleResolver resolver, String path) {
        this.path = path;
        this.service = service;
        this.resolver = resolver;
        this.volume = 1;
        this.looping = false;
    }

    @Override
    public void play() {
        if (music == null) {
            FileHandle file = resolver.resolve(path);
            music = service.newMusic(file);
            music.setLooping(looping);
            music.setVolume(volume);
            music.setOnCompletionListener(listener);
        }
        music.play();
    }

    @Override
    public void pause() {
        if (music != null) {
            music.pause();
        }
    }

    @Override
    public void stop() {
        if (music != null) {
            music.stop();
            dispose();
        }
    }

    @Override
    public boolean isPlaying() {
        if (music != null) {
            return music.isPlaying();
        }
        return false;
    }

    @Override
    public void setLooping(boolean isLooping) {
        looping = isLooping;
        if (music != null) {
            music.setLooping(isLooping);
        }
    }

    @Override
    public boolean isLooping() {
        return looping;
    }

    @Override
    public void setVolume(float volume) {
        this.volume = volume;
        if (music != null) {
            music.setVolume(volume);
        }
    }

    @Override
    public float getVolume() {
        return volume;
    }

    @Override
    public void setPan(float pan, float volume) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPosition(float position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getPosition() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dispose() {
        if (music != null) {
            music.dispose();
            music = null;
        }
    }

    @Override
    public void setOnCompletionListener(OnCompletionListener listener) {
        this.listener = listener;
        if (music != null) {
            music.setOnCompletionListener(listener);
        }
    }
}
