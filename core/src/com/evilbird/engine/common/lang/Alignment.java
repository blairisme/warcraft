/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
