/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft;

import android.content.Intent;
import android.os.Bundle;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.menu.intro.IntroMenuType;
import com.evilbird.warcraft.menu.main.MainMenuType;
import com.evilbird.warcraft.menu.outro.OutroMenuType;
import com.evilbird.warcraft.state.WarcraftCampaign;
import org.apache.commons.lang3.EnumUtils;

/**
 * Contains commands provided to the game when started.
 *
 * @author Blair Butterworth
 */
public class AndroidCommands
{
    private StateIdentifier scenario;
    private MenuIdentifier menu;
    private boolean quickBuild;
    private boolean freeBuild;
    private boolean upgradeCheat;

    public AndroidCommands(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            menu = getMenuValue(bundle, "menu");
            scenario = getScenarioValue(bundle, "scenario");
            quickBuild = getBooleanValue(bundle, "quick-build");
            freeBuild = getBooleanValue(bundle, "free-build");
            upgradeCheat = getBooleanValue(bundle, "all-upgrades");
        }
    }

    /**
     * The scenario to show when the game engine starts. This command line
     * option is optional and may be {@code null}.
     *
     * @return a {@link StateIdentifier}.
     */
    public StateIdentifier getScenario() {
        return scenario;
    }

    /**
     * The menu to show when the game engine starts. This command line
     * option is optional and may be {@code null}.
     *
     * @return a {@link MenuIdentifier}.
     */
    public MenuIdentifier getMenu() {
        return menu;
    }

    /**
     * Instructs the game engine to produce units and buildings quickly.
     *
     * @return {@code true} if enabled.
     */
    public boolean isQuickBuildEnabled() {
        return quickBuild;
    }

    /**
     * Instructs the game engine to produce units and buildings for free.
     *
     * @return {@code true} if enabled.
     */
    public boolean isFreeBuildEnabled() {
        return freeBuild;
    }

    /**
     * Instructs the game engine to make available all upgrades for free.
     *
     * @return {@code true} if enabled.
     */
    public boolean isUpgradeCheatEnabled() {
        return upgradeCheat;
    }

    private MenuIdentifier getMenuValue(Bundle bundle, String key) {
        String value = bundle.getString(key);

        if (value == null) {
            return null;
        }
        if (EnumUtils.isValidEnum(MainMenuType.class, value)) {
            return MainMenuType.valueOf(value);
        }
        if (EnumUtils.isValidEnum(IntroMenuType.class, value)) {
            return IntroMenuType.valueOf(value);
        }
        if (EnumUtils.isValidEnum(OutroMenuType.class, value)) {
            return OutroMenuType.valueOf(value);
        }
        throw new IllegalArgumentException("Unknown menu type: " + menu);
    }

    private StateIdentifier getScenarioValue(Bundle bundle, String key) {
        String value = bundle.getString(key);
        return WarcraftCampaign.valueOf(value);
    }

    private boolean getBooleanValue(Bundle bundle, String key) {
        return bundle.getBoolean(key);
    }
}
