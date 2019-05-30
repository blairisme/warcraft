/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.evilbird.engine.common.platform.ApplicationUtils.isMobile;

/**
 * https://github.com/czyzby/gdx-lml/blob/master/kiwi/src/main/java/com/github/czyzby/kiwi/util/gdx/viewport/Viewports.java
 *
 */
public class Viewports
{
    private Viewports() {
    }

    public static Viewport getDensityAwareViewport() {
        float density = Gdx.graphics.getDensity();
        float unitsPerPixel = isMobile() ? 1f / density : 96f / 160f / density;

        ScreenViewport viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(unitsPerPixel);

        return viewport;
    }
}
