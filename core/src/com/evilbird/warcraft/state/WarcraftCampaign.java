/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.IntroducedState;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.engine.state.StateSequence;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.menu.intro.IntroMenuType;
import com.evilbird.warcraft.menu.main.MainMenu;
import org.apache.commons.lang3.EnumUtils;

import static com.evilbird.engine.common.file.FileType.JSON;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;

/**
 * An identifier for a built-in campaign scenario.
 *
 * @author Blair Butterworth
 */
public enum WarcraftCampaign implements StateIdentifier, StateSequence, IntroducedState
{
    Human1,
    Human2,
    Human3,
    Human4,
    Human5,
    Human6,
    Human7,
    Human8,
    Human9,
    Human10,
    Human11,
    Human12,
    Human13,
    Human14,

    Orc1,
    Orc2,
    Orc3,
    Orc4,
    Orc5,
    Orc6,
    Orc7,
    Orc8,
    Orc9,
    Orc10,
    Orc11,
    Orc12,
    Orc13,
    Orc14;

    private static final String CAMPAIGN_FILE_NAME = "campaign";

    public WarcraftFaction getFaction() {
        return this.ordinal() >= Orc1.ordinal() ? Orc : Human;
    }

    public String getFactionName() {
        return getFaction().name().toLowerCase();
    }

    public String getFileName() {
        return CAMPAIGN_FILE_NAME + getIndex() + JSON.getFileExtension();
    }

    public int getIndex() {
        return ordinal() >= Orc1.ordinal() ? ordinal() - Orc1.ordinal() + 1 : ordinal() + 1;
    }

    @Override
    public MenuIdentifier getIntroductionMenu() {
        return IntroMenuType.valueOf(name());
    }

    @Override
    public StateIdentifier getNextState() {
        if (getIndex() < 14) {
            return WarcraftCampaign.values()[ordinal() + 1];
        }
        return null;
    }
}
