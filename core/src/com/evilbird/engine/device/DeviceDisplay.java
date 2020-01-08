/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.device;

/**
 * Implementors of this interface provide properties about the current display.
 *
 * @author Blair Butterworth
 */
public interface DeviceDisplay
{
    /**
     * Returns a value indicating the pixel density of the current display.
     * This follows the same conventions as android.util.DisplayMetrics#density,
     * where the returned value represents one pixel on an approximately 160
     * dpi screen. Thus on a 160dpi screen this density value will be 1; on a
     * 120 dpi screen it would be .75; etc.
     */
    float getDensity();

    /**
     * Returns a value indicating how many logical pixels belong to each
     * physical pixel.
     */
    float getPixelUnits();

    /**
     * Returns the screen resolution in dots-per-inch.
     */
    float getResolution();

    /**
     * Return the pixel scale factor of the Display window. When running in
     * high DPI mode the operating system will scale the Display window to
     * avoid the window shrinking due to high resolutions. Where high DPI mode
     * is not available this method will return 1.0f.
     */
    float getScaleFactor();
}
