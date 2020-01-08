/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.text;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Defines commonly used {@link BitmapFont Fonts}.
 *
 * @author Blair Butterworth
 */
public class Fonts
{
    /**
     * Defines a font using the Arial typeface, at 16 point size and in black.
     */
    public static final BitmapFont ARIAL = new BitmapFont();

    /**
     * Disable construction of this static utility class.
     */
    private Fonts() {
    }
}
