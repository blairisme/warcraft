/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
