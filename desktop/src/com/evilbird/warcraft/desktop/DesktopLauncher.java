/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.preferences.GamePreferences;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.desktop.DaggerDesktopInjector.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import picocli.CommandLine;

import static com.evilbird.warcraft.desktop.DesktopStorage.STORAGE_ROOT;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * Instances of this class represent the entry point for the desktop version of
 * the application.
 *
 * @author Blair Butterworth
 */
public class DesktopLauncher
{
    private DesktopLauncher() {
    }

    public static void main(String[] args) {
        configure();
        start(args);
    }

    private static void configure() {
        ToStringBuilder.setDefaultStyle(SHORT_PREFIX_STYLE);
    }

    private static void start(String[] args) {
        DesktopCommands commands = CommandLine.populateCommand(new DesktopCommands(), args);
        ApplicationListener engine = getEngine(commands);
        LwjglApplicationConfiguration configuration = getConfiguration();
        new LwjglApplication(engine, configuration);
    }

    private static ApplicationListener getEngine(DesktopCommands commands) {
        GameService service = getService();
        GameEngine engine = service.getEngine();
        configureEngine(engine, commands);
        configureService(service, commands);
        return engine;
    }

    private static void configureEngine(GameEngine engine, DesktopCommands commands) {
        MenuIdentifier menu = commands.getMenu();
        StateIdentifier state = commands.getState();

        if (menu != null && state != null) {
            engine.setInitialScreen(menu, state);
        }
        else if (state != null) {
            engine.setInitialScreen(state);
        }
        else if (menu != null) {
            engine.setInitialScreen(menu);
        }
    }

    private static void configureService(GameService service, DesktopCommands commands) {
        GamePreferences preferences = service.getPreferences();
        WarcraftPreferences warcraftPreferences = (WarcraftPreferences)preferences;
        warcraftPreferences.setBuildCostCheatEnabled(commands.isFreeBuildEnabled());
        warcraftPreferences.setBuildTimeCheatEnabled(commands.isQuickBuildEnabled());
        warcraftPreferences.setUpgradeCheatEnabled(commands.isUpgradeCheatEnabled());
        warcraftPreferences.setDebugControlEnabled(commands.isDebugControlEnabled());
    }

    private static GameService getService() {
        GameService service = GameService.getInstance();
        service.setInjector(getInjector());
        return service;
    }

    private static DesktopInjector getInjector() {
        Builder builder = DaggerDesktopInjector.builder();
        return builder.build();
    }

    private static LwjglApplicationConfiguration getConfiguration() {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title = "Warcraft II";
        configuration.height = 768;
        configuration.width = 1024;
        configuration.useHDPI = true;
        configuration.vSyncEnabled = true;
        configuration.preferencesDirectory = STORAGE_ROOT + "prefs";
        configuration.preferencesFileType = Files.FileType.External;
        configuration.addIcon("data/icons/WarcraftIcon.png", Files.FileType.Internal);
        return configuration;
    }
}
