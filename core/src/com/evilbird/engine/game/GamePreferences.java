/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Provides access to preferences used in game engine operation.
 *
 * @author Blair Butterworth
 */
@Singleton
public class GamePreferences
{
    private static final String IDENTIFIER = "engine";
    private static final String GAME_PAUSED = "game_paused";
    private static final String MUSIC_VOLUME = "music_volume";
    private static final String EFFECTS_VOLUME = "effects_volume";

    private static final boolean GAME_PAUSED_DEFAULT = false;
    private static final float MUSIC_VOLUME_DEFAULT = 1f;
    private static final float EFFECTS_VOLUME_DEFAULT = 1f;

    private Preferences store;

    @Inject
    public GamePreferences() {
        store = null;
    }

    public boolean getGamePaused() {
        Preferences preferences = getPreferences();
        return preferences.getBoolean(GAME_PAUSED, GAME_PAUSED_DEFAULT);
    }

    public float getMusicVolume() {
        Preferences preferences = getPreferences();
        return preferences.getFloat(MUSIC_VOLUME, MUSIC_VOLUME_DEFAULT);
    }

    public float getEffectsVolume() {
        Preferences preferences = getPreferences();
        return preferences.getFloat(EFFECTS_VOLUME, EFFECTS_VOLUME_DEFAULT);
    }

    public void setGamePaused(boolean paused) {
        Preferences preferences = getPreferences();
        preferences.putBoolean(GAME_PAUSED, paused);
    }

    public void setMusicVolume(float volume) {
        Preferences preferences = getPreferences();
        preferences.putFloat(MUSIC_VOLUME, volume);
    }

    public void setEffectsVolume(float volume) {
        Preferences preferences = getPreferences();
        preferences.putFloat(EFFECTS_VOLUME, volume);
    }

    private Preferences getPreferences() {
        if (store == null) {
            store = Gdx.app.getPreferences(IDENTIFIER);
        }
        return store;
    }
}
