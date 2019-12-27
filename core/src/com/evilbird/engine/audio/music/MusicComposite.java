/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.audio.music;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a audio stream comprised of individual audio streams played in
 * parallel. Methods are provided to play, pause and stop the music combination,
 * as well as to configure its playback.
 *
 * @author Blair Butterworth
 */
public class MusicComposite implements Music
{
    private List<Music> combination;
    private transient boolean playing;
    private transient List<MusicObserver> observers;

    /**
     * Constructs a new instance of this class given a collection of
     * {@link Music} to play in parallel.
     */
    public MusicComposite(Music ... combination) {
        this(Arrays.asList(combination));
    }

    /**
     * Constructs a new instance of this class given a collection of
     * {@link Music} to play in parallel.
     */
    public MusicComposite(List<Music> combination) {
        this.combination = combination;
        this.observers = new ArrayList<>(1);
        MusicObserver observer = new CompletionObserver();
        for (Music music: combination) {
            music.addObserver(observer);
        }
    }

    @Override
    public void addObserver(MusicObserver observer) {
        observers.add(observer);
    }

    @Override
    public void dispose() {
        for (Music music: combination) {
            music.dispose();
        }
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public void play() {
        if (! playing) {
            playing = true;
            for (Music music: combination) {
                music.play();
            }
            for (MusicObserver observer: observers) {
                observer.onStart(this);
            }
        }
    }

    @Override
    public void removeObserver(MusicObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void stop() {
        playing = false;
        for (Music music: combination) {
            music.stop();
        }
        for (MusicObserver observer: observers) {
            observer.onStop(this);
        }
    }

    @Override
    public void setVolume(float volume) {
        for (Music music: combination) {
            music.setVolume(volume);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        MusicComposite that = (MusicComposite) other;
        return new EqualsBuilder()
            .append(combination, that.combination)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(combination)
            .toHashCode();
    }

    private class CompletionObserver extends MusicObserverAdapter {
        @Override
        public void onComplete(Music music) {
            evaluateCompletion();
        }
    }

    private void evaluateCompletion() {
        playing = evaluatePlaying();
        if (! playing) {
            for (MusicObserver observer: observers) {
                observer.onComplete(this);
            }
        }
    }

    private boolean evaluatePlaying() {
        for (Music music: combination) {
            if (music.isPlaying()) {
                return true;
            }
        }
        return false;
    }
}
