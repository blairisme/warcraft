/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
        IOSApplicationConfiguration configuration = new IOSApplicationConfiguration();
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
}