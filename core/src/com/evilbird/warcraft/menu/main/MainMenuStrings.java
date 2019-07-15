/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.utils.I18NBundle;

/**
 * A language specific bundle of strings used in main menus.
 *
 * @author Blair Butterworth
 */
public class MainMenuStrings
{
    private I18NBundle bundle;

    public MainMenuStrings(I18NBundle bundle) {
        this.bundle = bundle;
    }

    public String getSinglePlayerButtonText() {
        return bundle.get("single-player");
    }

    public String getMultiPlayerButtonText() {
        return bundle.get("multi-player");
    }

    public String getReplyIntroButtonText() {
        return bundle.get("reply");
    }

    public String getShowCreditsButtonText() {
        return bundle.get("credits");
    }

    public String getExitButtonText() {
        return bundle.get("exit");
    }

    public String getLoadButtonText() {
        return bundle.get("load");
    }

    public String getNewCampaignButtonText() {
        return bundle.get("new-campaign");
    }

    public String getCustomCampaignButtonText() {
        return bundle.get("custom-campaign");
    }

    public String getHumanCampaignButtonText() {
        return bundle.get("human-campaign");
    }

    public String getOrdCampaignButtonText() {
        return bundle.get("orc-campaign");
    }

    public String getPreviousButtonText() {
        return bundle.get("previous-menu");
    }
}
