/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio;

import com.badlogic.gdx.audio.Music;

import java.util.Arrays;
import java.util.List;

public class MusicCombination implements Music
{
    private boolean playing;
    private List<Music> combination;

    public MusicCombination(Music ... combination) {
        this(Arrays.asList(combination));
    }

    public MusicCombination(List<Music> combination) {
        this.combination = combination;
    }

    @Override
    public void play() {
        if (! playing) {
            playing = true;
            for (Music music: combination) {
                music.play();
            }
        }
    }

    @Override
    public void pause() {
        playing = false;
        for (Music music: combination) {
            music.pause();
        }
    }

    @Override
    public void stop() {
        playing = false;
        for (Music music: combination) {
            music.stop();
        }
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public void dispose() {
        for (Music music: combination) {
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
