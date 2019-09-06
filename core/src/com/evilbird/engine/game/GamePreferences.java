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
    private static final String PAUSED = "paused";

    private Preferences store;

    @Inject
    public GamePreferences() {
    }

    public boolean getGamePaused() {
        Preferences preferences = getPreferences();
        return preferences.getBoolean(PAUSED);
    }

    public void setGamePaused(boolean paused) {
        Preferences preferences = getPreferences();
        preferences.putBoolean(PAUSED, paused);
    }

    private Preferences getPreferences() {
        if (store == null) {
            store = Gdx.app.getPreferences(IDENTIFIER);
        }
        return store;
    }
}
