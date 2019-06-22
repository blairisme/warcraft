/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.campaign;

import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.IntroducedState;
import com.evilbird.warcraft.menu.intro.IntroMenuType;
import com.evilbird.warcraft.menu.main.MainMenuType;
import com.evilbird.warcraft.state.WarcraftStateIdentifier;

/**
 * An identifier for a built-in campaign scenario.
 *
 * @author Blair Butterworth
 */
public enum WarcraftCampaign implements WarcraftStateIdentifier, IntroducedState
{
    Human1("data/levels/human/level1.json"),
    Human2("data/levels/human/level2.json"),
    Orc1("data/levels/orc/level1.json"),
    Orc2("data/levels/orc/level2.json");

    private String path;

    WarcraftCampaign(String path) {
        this.path = path;
    }

    public String getFilePath() {
        return path;
    }

    @Override
    public MenuIdentifier getIntroductionMenu() {
        switch(this) {
            case Orc1: return IntroMenuType.Orc1;
            case Orc2: return IntroMenuType.Orc2;
            case Human1: return IntroMenuType.Human1;
            case Human2: return IntroMenuType.Human2;
            default: return MainMenuType.Home;
        }
    }
}
