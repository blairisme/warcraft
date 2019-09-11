/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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

    public AndroidCommands(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            menu = getMenuValue(bundle, "menu");
            scenario = getScenarioValue(bundle, "scenario");
            quickBuild = getBooleanValue(bundle, "quick-build");
            freeBuild = getBooleanValue(bundle, "free-build");
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
