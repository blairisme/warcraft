/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.badlogic.gdx.Preferences;

import javax.inject.Inject;

import static com.badlogic.gdx.Gdx.app;

public class GamePreferences
{
    private static final String PAUSED_PREFERENCE = "paused";

    private Preferences store;

    @Inject
    public GamePreferences() {
    }

    public boolean getGamePaused() {
        Preferences preferences = getPreferences();
        return preferences.getBoolean(PAUSED_PREFERENCE);
    }

    public void setGamePaused(boolean paused) {
        Preferences preferences = getPreferences();
        preferences.putBoolean(PAUSED_PREFERENCE, paused);
    }

    private Preferences getPreferences() {
        if (store == null) {
            store = app.getPreferences("engine");
        }
        return store;
    }
}
