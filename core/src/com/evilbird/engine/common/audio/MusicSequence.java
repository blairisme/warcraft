/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio;

import com.badlogic.gdx.audio.Music;

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
    private Music current;
    private List<Music> sequence;
    private Iterator<Music> iterator;

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
        }
    }

    private void next() {
        if (current != null) {
            current.stop();
        }
        if (iterator.hasNext()) {
            current = iterator.next();
            current.setOnCompletionListener(new CompletionListener());
            current.play();
        }
        else {
            playing = false;
        }
    }

    private class CompletionListener implements OnCompletionListener {
        @Override
        public void onCompletion(Music music) {
            next();
        }
    }

    @Override
    public void pause() {
        current.pause();
        playing = false;
    }

    @Override
    public void stop() {
        current.stop();
        current = null;
        iterator = null;
        playing = false;
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public void dispose() {
        for (Music music: sequence) {
            music.dispose();
        }
    }

    @Override
    public void setLooping(boolean isLooping) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isLooping() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setVolume(float volume) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getVolume() {
        throw new UnsupportedOperationException();
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
    public void setOnCompletionListener(OnCompletionListener listener) {
        throw new UnsupportedOperationException();
    }
}
