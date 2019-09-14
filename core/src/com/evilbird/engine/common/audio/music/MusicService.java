/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.audio.music;

import com.badlogic.gdx.audio.Music;
import com.evilbird.engine.game.GamePreferences;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Objects;

/**
 * Controls {@link Music} played in the game engine. Specifically this class
 * attempts to ensure that only one {@code Music} track can be played at any
 * time and that its volume matches that specified by the user and persisted
 * in the game preference system.
 *
 * @author Blair Butterworth
 */
@Singleton
public class MusicService
{
    private Music music;
    private GamePreferences preferences;

    @Inject
    public MusicService(GamePreferences preferences) {
        this.preferences = preferences;
        this.music = new SilentMusic();
    }

    public void play(Music music) {
        Objects.requireNonNull(music);
        if (! this.music.equals(music)) {
            this.music.stop();
            this.music = music;
            this.music.setVolume(getVolume());
            this.music.play();
        }
    }

    public void stop() {
        music.stop();
    }

    public float getVolume() {
        return preferences.getMusicVolume();
    }

    public void setVolume(float volume) {
        Validate.inclusiveBetween(0, 1, volume);
        music.setVolume(volume);
        preferences.setMusicVolume(volume);
    }
}
