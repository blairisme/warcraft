/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.integration.device;

import com.evilbird.engine.device.DeviceDisplay;

import javax.inject.Inject;

/**
 * Provides information about the current display, running on a desktop
 * platform.
 *
 * @author Blair Butterworth
 */
public class IntegrationDisplay implements DeviceDisplay
{
    @Inject
    public IntegrationDisplay() {
    }

    @Override
    public float getDensity() {
         return 1;
    }

    @Override
    public float getPixelUnits() {
        return 1;
    }

    @Override
    public float getResolution() {
        return 1024;
    }

    @Override
    public float getScaleFactor() {
        return 1;
    }
}
