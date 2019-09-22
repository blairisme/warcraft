/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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

    private static void configureService(GameService service, DesktopCommands commands) {
        GamePreferences preferences = service.getPreferences();
        WarcraftPreferences warcraftPreferences = (WarcraftPreferences)preferences;
        warcraftPreferences.setFreeBuildEnabled(commands.isFreeBuildEnabled());
        warcraftPreferences.setQuickBuildEnabled(commands.isQuickBuildEnabled());
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
