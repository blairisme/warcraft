/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.ingame.variant;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.menu.ingame.IngameMenu;
import com.evilbird.warcraft.menu.ingame.IngameMenuStrings;
import org.apache.commons.lang3.tuple.Pair;

import static com.evilbird.warcraft.menu.ingame.IngameMenuDimensions.Normal;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Options;

/**
 * Represents the sound settings in-game menu. Controls are provided to set the
 * music and sound effects volumes as well as to set whether certain unit sound
 * effects are enabled or not.
 *
 * @author Blair Butterworth
 */
public class SoundsMenu extends IngameMenu
{
    private Slider musicVolume;
    private Slider effectsVolume;
    private CheckBox speechOn;
    private CheckBox acknowledgementOn;
    private CheckBox buildingSoundsOn;
    private WarcraftPreferences preferences;

    public SoundsMenu(IngameMenu menu, IngameMenuStrings strings, WarcraftPreferences preferences) {
        super(menu);
        this.preferences = preferences;
        setLayout(Normal);
        addTitle(strings);
        addVolumeSettings(strings, preferences);
        addEnabledSoundSettings(strings, preferences);
        addButtons(strings);
    }

    private void addTitle(IngameMenuStrings strings) {
        addTitle(strings.getSoundSettingsTitle());
    }

    private void addVolumeSettings(IngameMenuStrings strings, WarcraftPreferences preferences) {
        musicVolume = addSlider(strings.getMusicVolume(), preferences.getMusicVolume());
        effectsVolume = addSlider(strings.getEffectsVolume(), preferences.getEffectsVolume());
    }

    private void addEnabledSoundSettings(IngameMenuStrings strings, WarcraftPreferences preferences) {
        speechOn = addCheckbox(strings.getSpeechEnabled(), preferences.isSpeechEnabled());
        acknowledgementOn = addCheckbox(strings.getAcknowledgementEnabled(), preferences.isAcknowledgementEnabled());
        buildingSoundsOn = addCheckbox(strings.getBuildingSoundsEnabled(), preferences.isBuildingSoundsEnabled());
    }

    private void addButtons(IngameMenuStrings strings) {
        addSpacer();
        addButtonRow(
            Pair.of(strings.getOkButtonText(), this::onSave),
            Pair.of(strings.getCancelButtonText(), () -> showMenuOverlay(Options)));
    }

    private void onSave() {
        preferences.setMusicVolume(musicVolume.getValue());
        preferences.setEffectsVolume(effectsVolume.getValue());
        preferences.setSpeechEnabled(speechOn.isChecked());
        preferences.setAcknowledgementEnabled(acknowledgementOn.isChecked());
        preferences.setBuildingSoundsEnabled(buildingSoundsOn.isChecked());
        preferences.save();
        showState();
    }
}
