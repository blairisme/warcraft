/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft;

import com.badlogic.gdx.Gdx;
import com.evilbird.engine.device.DeviceDisplay;

/**
 * Provides information about the current display, running on the Android
 * platform.
 *
 * @author Blair Butterworth
 */
public class AndroidDisplay implements DeviceDisplay
{
    @Override
    public float getDensity() {
        return Gdx.graphics.getDensity();
    }

    @Override
    public float getPixelUnits() {
        float density = getDensity();
        return 1f / density;
    }

    @Override
    public float getResolution() {
        return Gdx.graphics.getPpiX();
    }

    @Override
    public float getScaleFactor() {
        return getDensity();
    }
}
