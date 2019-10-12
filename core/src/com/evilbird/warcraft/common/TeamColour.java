/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.common;

import com.badlogic.gdx.graphics.Color;

public enum TeamColour
{
    None,
    Blue,
    Orange,
    Purple,
    Red,
    White;

    public Color getGdxColour() {
        switch (this) {
            case Red: return Color.RED;
            case Blue: return Color.BLUE;
            default: throw new UnsupportedOperationException();
        }
    }
}
