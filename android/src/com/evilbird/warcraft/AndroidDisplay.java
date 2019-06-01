/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
}
