/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.evilbird.engine.device.DeviceDisplay;
import org.lwjgl.opengl.Display;

import java.awt.Toolkit;

/**
 * Provides information about the current display, running on a desktop
 * platform.
 *
 * @author Blair Butterworth
 */
public class DesktopDisplay implements DeviceDisplay
{
    @Override
    public float getDensity() {
         return getResolution() / 160f;
    }

    @Override
    public float getPixelUnits() {
        float density = getDensity();
        return 96f / 160f / density;
    }

    @Override
    public float getResolution() {
        float resolution = Toolkit.getDefaultToolkit().getScreenResolution();
        float scaleFactor = Display.getPixelScaleFactor();
        return resolution * scaleFactor;
    }
}
