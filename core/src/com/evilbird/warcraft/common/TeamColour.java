/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.common;

import com.badlogic.gdx.graphics.Color;
import com.evilbird.engine.common.graphics.Colours;

/**
 * Defines options for team colours, a colours used to highlight portions of
 * units and buildings to visually denote ownership.
 *
 * @author Blair Butterworth
 */
public enum TeamColour
{
    None,
    Black,
    Blue,
    Green,
    Orange,
    Pink,
    Purple,
    Red,
    Silver;

    public Color getGdxColour() {
        switch (this) {
            case None: return Color.BLACK;
            case Black: return Colours.MIDNIGHT_BLACK;
            case Blue: return Colours.ROYAL_BLUE;
            case Green: return Colours.TURQUOISE;
            case Orange: return Colours.CORAL;
            case Pink: return Colours.LAVENDER;
            case Purple: return Colours.FUCHSIA;
            case Red: return Colours.SCARLET;
            case Silver: return Colours.SILVER;
            default: throw new UnsupportedOperationException();
        }
    }
}
