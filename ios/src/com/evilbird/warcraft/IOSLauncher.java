package com.evilbird.warcraft;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameEngine;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

public class IOSLauncher extends IOSApplication.Delegate {
    @Override
    protected IOSApplication createApplication() {
        Device device = new IOSDevice();
        ApplicationListener delegate = new GameEngine(device);
        IOSApplicationConfiguration configuration = new IOSApplicationConfiguration();
        return new IOSApplication(delegate, configuration);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}