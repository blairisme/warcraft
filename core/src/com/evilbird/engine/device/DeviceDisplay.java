/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
}
