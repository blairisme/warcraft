/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.common;

import com.badlogic.gdx.Preferences;
import com.evilbird.engine.common.preferences.TransientPreferences;
import com.evilbird.engine.game.GamePreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.badlogic.gdx.Gdx.app;

@Singleton
public class WarcraftPreferences extends GamePreferences
{
    private static final String IDENTIFIER = "warcraft";
    private static final String FREE_BUILD = "freeBuild";
    private static final String QUICK_BUILD = "freeBuild";

    private Preferences persistedPreferences;
    private Preferences transientPreferences;

    @Inject
    public WarcraftPreferences() {
    }

    public boolean isQuickBuildEnabled() {
        Preferences preferences = getTransientPreferences();
        return preferences.getBoolean(QUICK_BUILD);
    }

    public boolean isFreeBuildEnabled() {
        Preferences preferences = getTransientPreferences();
        return preferences.getBoolean(FREE_BUILD);
    }

    public void setFreeBuildEnabled(boolean enabled) {
        Preferences preferences = getTransientPreferences();
        preferences.putBoolean(FREE_BUILD, enabled);
    }

    public void setQuickBuildEnabled(boolean enabled) {
        Preferences preferences = getTransientPreferences();
        preferences.putBoolean(QUICK_BUILD, enabled);
    }

    private Preferences getTransientPreferences() {
        if (transientPreferences == null) {
            transientPreferences = new TransientPreferences();
        }
        return transientPreferences;
    }

    private Preferences getPersistedPreference() {
        if (persistedPreferences == null) {
            persistedPreferences = app.getPreferences(IDENTIFIER);
        }
        return persistedPreferences;
    }
}
