/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.desktop;

import com.evilbird.engine.device.DeviceDisplay;
import org.lwjgl.opengl.Display;

import javax.inject.Inject;
import java.awt.*;

/**
 * Provides information about the current display, running on a desktop
 * platform.
 *
 * @author Blair Butterworth
 */
public class DesktopDisplay implements DeviceDisplay
{
    private float scaleFactor;

    @Inject
    public DesktopDisplay() {
        scaleFactor = -1;
    }

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

    @Override
    public float getScaleFactor() {
        if (scaleFactor == -1) {
            scaleFactor = Display.getPixelScaleFactor();
        }
        return scaleFactor;
    }
}
