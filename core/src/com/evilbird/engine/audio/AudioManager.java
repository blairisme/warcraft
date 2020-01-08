/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.audio;

import com.evilbird.engine.audio.music.Music;
import com.evilbird.engine.preferences.GamePreferences;
import com.evilbird.engine.preferences.GamePreferencesObserver;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static com.evilbird.engine.common.collection.CollectionUtils.retainIf;

/**
 * Controls {@link Audio} played in the game engine. Specifically this class
 * controls which {@code Audio} is played at any time and that its volume
 * matches that specified by the user and persisted in the game preference
 * system.
 *
 * @author Blair Butterworth
 */
@Singleton
public class AudioManager
{
    private Collection<Audio> sounds;
    private GamePreferences preferences;

    @Inject
    public AudioManager(GamePreferences preferences) {
        Objects.requireNonNull(preferences);
        this.sounds = new ArrayList<>();
        this.preferences = preferences;
        this.preferences.addObserver(new PreferenceObserver());
    }

    /**
     * Returns whether the given {@link Audio Sound} is current playing.
     */
    public boolean isPlaying(Audio sound) {
        Objects.requireNonNull(sound);
        retainIf(sounds, Audio::isPlaying);
        return sounds.contains(sound);
    }

    /**
     * Plays the given {@link Audio Sound}. If the {@code Sound} is already
     * playing, then resulting behaviour is undefined. {@code Music}
     * implementations will be restarted, whilst {@code Sound} will play a new
     * instance in parallel.
     */
    public void play(Audio sound) {
        Objects.requireNonNull(sound);
        retainIf(sounds, Audio::isPlaying);
        sounds.add(sound);
        sound.play();
        updateVolume(sound);
    }

    /**
     * Stops playing the given {@link Audio Sound}. If the {@code Sound}} is no
     * longer playing this has no effect.
     */
    public void stop(Audio sound) {
        Objects.requireNonNull(sound);
        retainIf(sounds, Audio::isPlaying);
        sounds.remove(sound);
        sound.stop();
    }

    public void stop() {
        for (Audio sound: sounds) {
            sound.stop();
        }
        sounds.clear();
    }

    private void updateVolume(Audio sound) {
        if (sound instanceof Music) {
            sound.setVolume(preferences.getMusicVolume());
        } else {
            sound.setVolume(preferences.getEffectsVolume());
        }
    }

    private class PreferenceObserver implements GamePreferencesObserver {
        @Override
        public void onPreferencesSaved(GamePreferences preferences) {
            for (Audio sound: sounds) {
                updateVolume(sound);
            }
        }
    }
}
