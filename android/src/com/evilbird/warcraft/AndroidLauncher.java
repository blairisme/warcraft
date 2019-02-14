/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import android.os.Bundle;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.evilbird.warcraft.DaggerAndroidInjector.Builder;

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

    private static ApplicationListener getEngine() {
        AndroidInjector injector = getInjector();
        return injector.newGameEngine();
    }

    private static AndroidApplicationConfiguration getConfiguration() {
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.hideStatusBar = true;
        return configuration;
    }
}
