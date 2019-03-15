/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.Gdx;

public class DensityIndependentPixel
{
    private static final float density = Gdx.graphics.getDensity();

    private DensityIndependentPixel() {
    }

    public static int dp(float value) {
        return Math.round(density * value);
    }
}
