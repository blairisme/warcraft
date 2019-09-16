/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame.variant;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.menu.ingame.IngameMenu;
import com.evilbird.warcraft.menu.ingame.IngameMenuStrings;
import org.apache.commons.lang3.tuple.Pair;

import static com.evilbird.warcraft.menu.ingame.IngameMenuDimensions.Normal;

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
        addSpacer();
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
        addButtonRow(
            Pair.of(strings.getOkButtonText(), this::onSave),
            Pair.of(strings.getCancelButtonText(), this::onCancel));
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

    private void onCancel() {
        showState();
    }
}
