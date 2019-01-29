/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import com.badlogic.gdx.utils.Align;

/**
 * Defines options specifying alignment.
 *
 * @author Blair Butterworth
 */
public enum Alignment
{
    Center,
    Top,
    Bottom,
    Left,
    Right,
    TopLeft,
    TopRight,
    BottomLeft,
    BottomRight;

    public int toGdxAlign() {
        switch(this) {
            case Center: return Align.center;
            case Top: return Align.top;
            case Bottom: return Align.bottom;
            case Left: return Align.left;
            case Right: return Align.right;
            case TopLeft: return Align.topLeft;
            case TopRight: return Align.topRight;
            case BottomLeft: return Align.bottomLeft;
            case BottomRight: return Align.bottomRight;
            default: throw new UnsupportedOperationException();
        }
    }
}
