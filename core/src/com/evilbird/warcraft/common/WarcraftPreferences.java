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
import com.evilbird.engine.preferences.GamePreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.badlogic.gdx.Gdx.app;

/**
 * Provides access to preferences used in the Warcraft game.
 *
 * @author Blair Butterworth
 */
@Singleton
public class WarcraftPreferences extends GamePreferences
{
    private static final String IDENTIFIER = "warcraft";
    private static final String FREE_BUILD = "freeBuild";
    private static final String QUICK_BUILD = "quickBuild";
    private static final String DEBUG_CONTROL = "debugControl";
    private static final String SPEECH_ENABLED = "speechEnabled";
    private static final String ACKNOWLEDGEMENT_ENABLED = "acknowledgementEnabled";
    private static final String BUILDING_SOUNDS_ENABLED = "buildingSoundsEnabled";

    private static final boolean FREE_BUILD_DEFAULT = false;
    private static final boolean QUICK_BUILD_DEFAULT = false;
    private static final boolean DEBUG_CONTROL_DEFAULT = false;
    private static final boolean SPEECH_ENABLED_DEFAULT = true;
    private static final boolean ACKNOWLEDGEMENT_ENABLED_DEFAULT = true;
    private static final boolean BUILDING_SOUNDS_ENABLED_DEFAULT = true;

    private Preferences persistedPreferences;
    private Preferences transientPreferences;

    @Inject
    public WarcraftPreferences() {
    }

    public boolean isDebugControlEnabled() {
        Preferences preferences = getTransientPreferences();
        return preferences.getBoolean(DEBUG_CONTROL, DEBUG_CONTROL_DEFAULT);
    }

    public boolean isQuickBuildEnabled() {
        Preferences preferences = getTransientPreferences();
        return preferences.getBoolean(QUICK_BUILD, QUICK_BUILD_DEFAULT);
    }

    public boolean isFreeBuildEnabled() {
        Preferences preferences = getTransientPreferences();
        return preferences.getBoolean(FREE_BUILD, FREE_BUILD_DEFAULT);
    }

    public boolean isSpeechEnabled() {
        Preferences preferences = getPersistedPreferences();
        return preferences.getBoolean(SPEECH_ENABLED, SPEECH_ENABLED_DEFAULT);
    }

    public boolean isAcknowledgementEnabled() {
        Preferences preferences = getPersistedPreferences();
        return preferences.getBoolean(ACKNOWLEDGEMENT_ENABLED, ACKNOWLEDGEMENT_ENABLED_DEFAULT);
    }

    public boolean isBuildingSoundsEnabled() {
        Preferences preferences = getPersistedPreferences();
        return preferences.getBoolean(BUILDING_SOUNDS_ENABLED, BUILDING_SOUNDS_ENABLED_DEFAULT);
    }

    public void setDebugControlEnabled(boolean enabled) {
        Preferences preferences = getTransientPreferences();
        preferences.putBoolean(DEBUG_CONTROL, enabled);
    }

    public void setFreeBuildEnabled(boolean enabled) {
        Preferences preferences = getTransientPreferences();
        preferences.putBoolean(FREE_BUILD, enabled);
    }

    public void setQuickBuildEnabled(boolean enabled) {
        Preferences preferences = getTransientPreferences();
        preferences.putBoolean(QUICK_BUILD, enabled);
    }

    public void setSpeechEnabled(boolean enabled) {
        Preferences preferences = getPersistedPreferences();
        preferences.putBoolean(SPEECH_ENABLED, enabled);
    }

    public void setAcknowledgementEnabled(boolean enabled) {
        Preferences preferences = getPersistedPreferences();
        preferences.putBoolean(ACKNOWLEDGEMENT_ENABLED, enabled);
    }

    public void setBuildingSoundsEnabled(boolean enabled) {
        Preferences preferences = getPersistedPreferences();
        preferences.putBoolean(BUILDING_SOUNDS_ENABLED, enabled);
    }

    @Override
    public void save() {
        Preferences preferences = getPersistedPreferences();
        preferences.flush();
        super.save();
    }

    private Preferences getTransientPreferences() {
        if (transientPreferences == null) {
            transientPreferences = new TransientPreferences();
        }
        return transientPreferences;
    }

    private Preferences getPersistedPreferences() {
        if (persistedPreferences == null) {
            persistedPreferences = app.getPreferences(IDENTIFIER);
        }
        return persistedPreferences;
    }
}
