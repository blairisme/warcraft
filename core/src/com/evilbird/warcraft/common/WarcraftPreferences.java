/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
    private static final String BUILD_COST_CHEAT = "buildCostCheat";
    private static final String BUILD_TIME_CHEAT = "buildTimeCheat";
    private static final String REVEAL_CHEAT = "revealMapCheat";
    private static final String UPGRADE_CHEAT = "upgradeCheat";
    private static final String DEBUG_CONTROL = "debugControl";
    private static final String SPEECH_ENABLED = "speechEnabled";
    private static final String ACKNOWLEDGEMENT_ENABLED = "acknowledgementEnabled";
    private static final String BUILDING_SOUNDS_ENABLED = "buildingSoundsEnabled";

    private static final boolean BUILD_COST_CHEAT_DEFAULT = false;
    private static final boolean BUILD_TIME_CHEAT_DEFAULT = false;
    private static final boolean REVEAL_CHEAT_DEFAULT = false;
    private static final boolean UPGRADE_CHEAT_DEFAULT = false;
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

    public boolean isBuildCostCheatEnabled() {
        Preferences preferences = getTransientPreferences();
        return preferences.getBoolean(BUILD_COST_CHEAT, BUILD_COST_CHEAT_DEFAULT);
    }

    public boolean isBuildTimeCheatEnabled() {
        Preferences preferences = getTransientPreferences();
        return preferences.getBoolean(BUILD_TIME_CHEAT, BUILD_TIME_CHEAT_DEFAULT);
    }

    public boolean isRevealMapCheatEnabled() {
        Preferences preferences = getTransientPreferences();
        return preferences.getBoolean(REVEAL_CHEAT, REVEAL_CHEAT_DEFAULT);
    }

    public boolean isUpgradeCheatEnabled() {
        Preferences preferences = getTransientPreferences();
        return preferences.getBoolean(UPGRADE_CHEAT, UPGRADE_CHEAT_DEFAULT);
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

    public void setBuildCostCheatEnabled(boolean enabled) {
        Preferences preferences = getTransientPreferences();
        preferences.putBoolean(BUILD_COST_CHEAT, enabled);
    }

    public void setBuildTimeCheatEnabled(boolean enabled) {
        Preferences preferences = getTransientPreferences();
        preferences.putBoolean(BUILD_TIME_CHEAT, enabled);
    }

    public void setRevealMapCheatEnabled(boolean enabled) {
        Preferences preferences = getTransientPreferences();
        preferences.putBoolean(REVEAL_CHEAT, enabled);
    }

    public void setUpgradeCheatEnabled(boolean enabled) {
        Preferences preferences = getTransientPreferences();
        preferences.putBoolean(UPGRADE_CHEAT, enabled);
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
