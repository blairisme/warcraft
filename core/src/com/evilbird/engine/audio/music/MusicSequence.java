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
import java.util.Objects;

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
        Objects.requireNonNull(sequence);
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
    public void dispose() {
        for (Music music: sequence) {
            music.dispose();
        }
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public void play() {
        playing = true;

        if (iterator == null) {
            iterator = sequence.iterator();
        }
        if (current == null) {
            next();
        }
        if (current != null && !current.isPlaying()) {
            current.play();

            for (MusicObserver observer: observers) {
                observer.onStart(this);
            }
        }
    }

    public void next() {
        if (current != null) {
            current.stop();
            //current.dispose();
        }
        if (iterator.hasNext()) {
            current = iterator.next();
            current.play();
        }
        else {
            complete();
        }
    }

    protected void complete() {
        if (looping) {
            iterator = sequence.iterator();
        } else {
            playing = false;
            for (MusicObserver observer: observers) {
                observer.onComplete(this);
            }
        }
    }

    @Override
    public void removeObserver(MusicObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void stop() {
        if (current != null) {
            current.stop();
            current = null;
            iterator = null;
            playing = false;

            for (MusicObserver observer: observers) {
                observer.onStop(this);
            }
        }
    }

    @Override
    public void setVolume(float volume) {
        Validate.inclusiveBetween(0, 1, volume);
        for (Music music: sequence){
            music.setVolume(volume);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

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

    private class CompletionObserver extends MusicObserverAdapter {
        @Override
        public void onComplete(Music music) {
            next();
        }
    }
}
