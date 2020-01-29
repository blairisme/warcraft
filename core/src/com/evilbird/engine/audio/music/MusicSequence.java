/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio.music;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a sequence of steamed audio, where each audio steam is played one
 * after the other. Methods are provided to play, pause and stop the music
 * sequence, as well as to configure its playback.
 *
 * @author Blair Butterworth
 */
public class MusicSequence implements Music
{
    private boolean playing;
    private boolean looping;
    private Music current;
    private List<Music> sequence;
    private Iterator<Music> iterator;
    private List<MusicObserver> observers;

    /**
     * Constructs a new instance of this class given an ordered collection of
     * music to play in sequence.
     *
     * @param sequence a {@link List} of {@link Music}. This parameter cannot
     *                 be {@code null}.
     */
    public MusicSequence(List<Music> sequence) {
        Validate.notNull(sequence);
        Validate.notEmpty(sequence);

        this.sequence = sequence;
        this.playing = false;
        this.looping = false;
        this.observers = new ArrayList<>(1);

        MusicObserver observer = new CompletionObserver();
        for (Music music: sequence) {
            music.addObserver(observer);
        }
    }

    @Override
    public void addObserver(MusicObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MusicObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void dispose() {
        for (Music music: sequence) {
            music.dispose();
        }
    }

    public boolean isLooping() {
        return looping;
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public void play() {
        if (!isPlaying()) {
            playing = true;
            loadSequence();
            next();
            notifyStarted();
        }
    }

    public void next() {
        if (isPlaying()) {
            unloadCurrent();
            if (! loadNext()) {
                if (isLooping()) {
                    resetSequence();
                    loadNext();
                } else {
                    unloadSequence();
                    notifyComplete();
                }
            }
        }
    }

    @Override
    public void stop() {
        if (isPlaying()) {
            unloadCurrent();
            unloadSequence();
            notifyStopped();
        }
    }

    @Override
    public void setVolume(float volume) {
        Validate.inclusiveBetween(0f, 1f, volume);
        for (Music music: sequence) {
            music.setVolume(volume);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) { return false; }
        if (other == this) { return true; }
        if (other.getClass() != getClass()) { return false; }

        MusicSequence that = (MusicSequence) other;
        return new EqualsBuilder()
            .append(current, that.current)
            .append(sequence, that.sequence)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(current)
            .append(sequence)
            .toHashCode();
    }

    private void loadSequence() {
        if (iterator == null) {
            iterator = sequence.iterator();
        }
    }

    private boolean loadNext() {
        if (iterator.hasNext()) {
            current = iterator.next();
            current.play();
            return true;
        }
        return false;
    }

    private void unloadCurrent() {
        if (current != null) {
            current.stop();
            current.dispose();
            current = null;
        }
    }

    private void unloadSequence() {
        playing = false;
        iterator = null;
    }

    private void resetSequence() {
        playing = true;
        iterator = sequence.iterator();
    }

    private void notifyStarted() {
        for (MusicObserver observer: observers) {
            observer.onStart(this);
        }
    }

    private void notifyStopped() {
        for (MusicObserver observer: observers) {
            observer.onStop(this);
        }
    }

    private void notifyComplete() {
        for (MusicObserver observer: observers) {
            observer.onComplete(this);
        }
    }

    private class CompletionObserver extends MusicObserverAdapter {
        @Override
        public void onComplete(Music music) {
            next();
        }
    }
}
