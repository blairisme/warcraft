/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.graphics;

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
