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
import com.evilbird.warcraft.desktop.DaggerDesktopInjector.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import picocli.CommandLine;

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

    private static DesktopInjector getInjector() {
        Builder builder = DaggerDesktopInjector.builder();
        return builder.build();
    }

    private static GameService getService() {
        GameService service = GameService.getInstance();
        service.setInjector(getInjector());
        return service;
    }

    private static ApplicationListener getEngine(DesktopCommands commands) {
        GameService service = getService();
        GameEngine engine = service.getGameEngine();
        if (commands.getScenario() != null) {
            engine.setInitialScreen(commands.getScenario());
        }
        if (commands.getMenu() != null) {
            engine.setInitialScreen(commands.getMenu());
        }
        return engine;
    }

    private static LwjglApplicationConfiguration getConfiguration() {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.height = 768;
        configuration.width = 1024;
        configuration.vSyncEnabled = true;
        configuration.title = "Warcraft II";
        configuration.addIcon("data/icons/WarcraftIcon.png", Files.FileType.Internal);
        return configuration;
    }
}
