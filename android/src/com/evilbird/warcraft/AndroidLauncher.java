/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft;

import android.os.Bundle;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.preferences.GamePreferences;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.DaggerAndroidInjector.Builder;
import com.evilbird.warcraft.common.WarcraftPreferences;

/**
 * Instances of this class represent the entry point for the Android version of
 * the application.
 *
 * @author Blair Butterworth
 */
public class AndroidLauncher extends AndroidApplication
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationListener engine = getEngine();
        AndroidApplicationConfiguration configuration = getConfiguration();
        initialize(engine, configuration);
    }

    private ApplicationListener getEngine() {
        GameService service = getService();
        GameEngine engine = service.getEngine();
        AndroidCommands commands = new AndroidCommands(getIntent());
        configureService(service, commands);
        configureEngine(engine, commands);
        return engine;
    }

    private GameService getService() {
        GameService service = GameService.getInstance();
        service.setInjector(getInjector());
        return service;
    }

    private AndroidInjector getInjector() {
        Builder builder = DaggerAndroidInjector.builder();
        return builder.build();
    }

    private AndroidApplicationConfiguration getConfiguration() {
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.hideStatusBar = true;
        return configuration;
    }

    private void configureEngine(GameEngine engine, AndroidCommands commands) {
        MenuIdentifier menu = commands.getMenu();
        StateIdentifier scenario = commands.getScenario();

        if (menu != null && scenario != null) {
            engine.setInitialScreen(menu, scenario);
        }
        else if (scenario != null) {
            engine.setInitialScreen(scenario);
        }
        else if (menu != null) {
            engine.setInitialScreen(menu);
        }
    }

    private void configureService(GameService service, AndroidCommands commands) {
        GamePreferences preferences = service.getPreferences();
        WarcraftPreferences warcraftPreferences = (WarcraftPreferences)preferences;
        warcraftPreferences.setBuildCostCheatEnabled(commands.isFreeBuildEnabled());
        warcraftPreferences.setBuildTimeCheatEnabled(commands.isQuickBuildEnabled());
        warcraftPreferences.setUpgradeCheatEnabled(commands.isUpgradeCheatEnabled());
    }
}
