/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.platform;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

public class ApplicationUtils
{
    private ApplicationUtils() {
    }

    public static boolean isAndroid() {
        return Gdx.app.getType() == Application.ApplicationType.Android;
    }

    public static boolean isMobile() {
        return isAndroid() || isIos();
    }

    public static boolean isDesktop() {
        return Gdx.app.getType() == Application.ApplicationType.Desktop;
    }

    public static boolean isIos() {
        return Gdx.app.getType() == Application.ApplicationType.iOS;
    }
}
