/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.evilbird.engine.device.DeviceDisplay;

/**
 * Provides information about the current display, running on the Ios
 * platform.
 *
 * @author Blair Butterworth
 */
public class IosDisplay implements DeviceDisplay
{
    @Override
    public float getDensity() {
        return 0;
    }

    @Override
    public float getPixelUnits() {
        return 0;
    }

    @Override
    public float getResolution() {
        return 0;
    }
}
