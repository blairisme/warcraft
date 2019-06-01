/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import android.os.Bundle;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.evilbird.engine.game.GameService;
import com.evilbird.warcraft.DaggerAndroidInjector.Builder;

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

    private static AndroidInjector getInjector() {
        Builder builder = DaggerAndroidInjector.builder();
        return builder.build();
    }

    private static GameService getService() {
        GameService service = GameService.getInstance();
        service.setInjector(getInjector());
        return service;
    }

    private static ApplicationListener getEngine() {
        GameService service = getService();
        return service.getEngine();
    }

    private static AndroidApplicationConfiguration getConfiguration() {
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.hideStatusBar = true;
        return configuration;
    }
}
