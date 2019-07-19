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

    public String getSinglePlayer() {
        return bundle.get("single-player");
    }

    public String getMultiPlayer() {
        return bundle.get("multi-player");
    }

    public String getReplyIntro() {
        return bundle.get("reply");
    }

    public String getShowCredits() {
        return bundle.get("credits");
    }

    public String getExit() {
        return bundle.get("exit");
    }

    public String getLoad() {
        return bundle.get("load");
    }

    public String getNewCampaign() {
        return bundle.get("new-campaign");
    }

    public String getCustomCampaign() {
        return bundle.get("custom-campaign");
    }

    public String getHumanCampaign() {
        return bundle.get("human-campaign");
    }

    public String getOrcCampaign() {
        return bundle.get("orc-campaign");
    }

    public String getPrevious() {
        return bundle.get("previous-menu");
    }
}
