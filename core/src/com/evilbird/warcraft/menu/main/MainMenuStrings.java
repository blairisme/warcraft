/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
