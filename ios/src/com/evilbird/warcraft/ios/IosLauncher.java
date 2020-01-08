/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.ios;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.evilbird.engine.game.GameService;
import com.evilbird.warcraft.ios.DaggerIosInjector.Builder;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

/**
 * Instances of this class represent the entry point for the iOS version of
 * the application.
 *
 * @author Blair Butterworth
 */
public class IosLauncher extends IOSApplication.Delegate
{
    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IosLauncher.class);
        pool.close();
    }

    @Override
    protected IOSApplication createApplication() {
        ApplicationListener delegate = getEngine();
        IOSApplicationConfiguration configuration = getConfiguration();
        return new IOSApplication(delegate, configuration);
    }

    private static ApplicationListener getEngine() {
        GameService service = getService();
        return service.getEngine();
    }

    private static GameService getService() {
        GameService service = GameService.getInstance();
        service.setInjector(getInjector());
        return service;
    }

    private static IosInjector getInjector() {
        Builder builder = DaggerIosInjector.builder();
        return builder.build();
    }

    private static IOSApplicationConfiguration getConfiguration() {
        IOSApplicationConfiguration configuration = new IOSApplicationConfiguration();
        configuration.orientationLandscape = true;
        configuration.preventScreenDimming = true;
        configuration.hideHomeIndicator = true;
        return configuration;
    }
}