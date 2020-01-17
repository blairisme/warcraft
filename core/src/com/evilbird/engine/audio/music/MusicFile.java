/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.music;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.files.FileHandle;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a streamed audio file that's loaded at the point its played.
 *
 * @author Blair Butterworth
 */
public class MusicFile implements Music
{
    private String path;
    private float volume;
    private transient Audio service;
    private transient FileHandleResolver resolver;
    private transient List<MusicObserver> observers;
    private transient com.badlogic.gdx.audio.Music delegate;

    public MusicFile(FileHandleResolver resolver, String path) {
        this(Gdx.audio, resolver, path);
    }

    public MusicFile(Audio service, FileHandleResolver resolver, String path) {
        this.path = path;
        this.volume = 1f;
        this.service = service;
        this.resolver = resolver;
        this.observers = new ArrayList<>(1);
    }

    @Override
    public void addObserver(MusicObserver observer) {
        observers.add(observer);
    }

    @Override
    public void dispose() {
        if (delegate != null) {
            delegate.dispose();
            delegate = null;
        }
    }

    @Override
    public boolean isPlaying() {
        if (delegate != null) {
            return delegate.isPlaying();
        }
        return false;
    }

    @Override
    public void play() {
        if (delegate == null) {
            FileHandle file = resolver.resolve(path);
            delegate = service.newMusic(file);
            delegate.setVolume(volume);
            delegate.setLooping(false);
            delegate.setOnCompletionListener(new CompletionObserver());
        }
        if (! delegate.isPlaying()) {
            delegate.play();
            for (MusicObserver observer: observers) {
                observer.onStart(this);
            }
        }
    }

    @Override
    public void stop() {
        if (delegate != null && delegate.isPlaying()) {
            delegate.stop();
            for (MusicObserver observer: observers) {
                observer.onStop(this);
            }
        }
    }

    @Override
    public void removeObserver(MusicObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void setVolume(float volume) {
        Validate.inclusiveBetween(0, 1, volume);
        this.volume = volume;
        if (delegate != null) {
            delegate.setVolume(volume);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        MusicFile musicFile = (MusicFile) other;
        return new EqualsBuilder()
            .append(path, musicFile.path)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(path)
            .toHashCode();
    }

    private void evaluateCompletion() {
        for (MusicObserver observer: observers) {
            observer.onComplete(this);
        }
    }

    private class CompletionObserver implements OnCompletionListener {
        @Override
        public void onCompletion(com.badlogic.gdx.audio.Music music) {
            evaluateCompletion();
        }
    }
}
