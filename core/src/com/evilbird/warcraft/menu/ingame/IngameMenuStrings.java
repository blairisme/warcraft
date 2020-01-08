/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.utils.I18NBundle;

/**
 * A language specific bundle of strings used in in-game menus.
 *
 * @author Blair Butterworth
 */
public class IngameMenuStrings
{
    private I18NBundle bundle;
    private I18NBundle objectives;

    public IngameMenuStrings(I18NBundle general, I18NBundle objectives) {
        this.bundle = general;
        this.objectives = objectives;
    }

    public String getMainTitle() {
        return bundle.get("main-title");
    }

    public String getLoadTitle() {
        return bundle.get("load-title");
    }

    public String getSaveTitle() {
        return bundle.get("save-title");
    }

    public String getOptionsTitle() {
        return bundle.get("options-title");
    }

    public String getVictoryTitle() {
        return bundle.get("victory-title");
    }

    public String getDefeatTitle() {
        return bundle.get("defeat-title");
    }

    public String getSurrenderTitle() {
        return bundle.get("surrender-title");
    }

    public String getSoundSettingsTitle() {
        return bundle.get("sound-settings-title");
    }

    public String getSpeedSettingsTitle() {
        return bundle.get("speed-settings-title");
    }

    public String getPreferenceTitle() {
        return bundle.get("preferences-title");
    }

    public String getObjectivesTitle() {
        return bundle.get("objectives-title");
    }

    public String getOkButtonText() {
        return bundle.get("ok");
    }

    public String getCancelButtonText() {
        return bundle.get("cancel");
    }

    public String getPreviousButtonText() {
        return bundle.get("previous");
    }

    public String getSaveButtonText() {
        return bundle.get("save");
    }

    public String getLoadButtonText() {
        return bundle.get("load");
    }

    public String getDeleteButtonText() {
        return bundle.get("delete");
    }

    public String getOptionsButtonText() {
        return bundle.get("options");
    }

    public String getObjectivesButtonText() {
        return bundle.get("objectives");
    }

    public String getObjectivesDefault() {
        return bundle.get("objectives-default");
    }

    public String getEndButtonText() {
        return bundle.get("end");
    }

    public String getReturnButtonText() {
        return bundle.get("return");
    }

    public String getRestartButtonText() {
        return bundle.get("restart");
    }

    public String getSurrenderButtonText() {
        return bundle.get("surrender");
    }

    public String getQuitButtonText() {
        return bundle.get("quit");
    }

    public String getExitButtonText() {
        return bundle.get("exit");
    }

    public String getSoundsButtonText() {
        return bundle.get("sounds");
    }

    public String getSpeedsButtonText() {
        return bundle.get("speeds");
    }

    public String getPreferencesButtonText() {
        return bundle.get("preferences");
    }

    public String getMusicVolume() {
        return bundle.get("music-volume");
    }

    public String getEffectsVolume() {
        return bundle.get("effects-volume");
    }

    public String getSpeechEnabled() {
        return bundle.get("speech-enabled");
    }

    public String getAcknowledgementEnabled() {
        return bundle.get("acknowledgement-enabled");
    }

    public String getBuildingSoundsEnabled() {
        return bundle.get("building-sounds-enabled");
    }

    public String getObjectives(int level) {
        return objectives.get("intro" + level);
    }

    public String getNewSaveFailed() {
        return bundle.get("save-new-failed");
    }

    public String getListSavesFailed() {
        return bundle.get("save-list-failed");
    }

    public String getLoadSaveFailed() {
        return bundle.get("save-load-failed");
    }

    public String getRemoveSaveFailed() {
        return bundle.get("save-delete-failed");
    }

    public String getSaveNotSelectedError() {
        return bundle.get("save-not-selected");
    }

    public String getSaveMissingNameError() {
        return bundle.get("save-missing-name");
    }

    public String getSaveInvalidNameError() {
        return bundle.get("save-invalid-name");
    }
}
