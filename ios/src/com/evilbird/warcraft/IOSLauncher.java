/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

public class IOSLauncher extends IOSApplication.Delegate
{
    @Override
    protected IOSApplication createApplication() {
        /*
        Device device = new IOSDevice();
        ApplicationListener delegate = new GameEngine(device);
        IOSApplicationConfiguration configuration = new IOSApplicationConfiguration();
        return new IOSApplication(delegate, configuration);
        */
        throw new UnsupportedOperationException();
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}