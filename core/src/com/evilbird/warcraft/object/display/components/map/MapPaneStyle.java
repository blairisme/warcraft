/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.math.Vector2.Zero;

/**
 * Defines the visual presentation of a {@link MapPane}.
 *
 * @author Blair Butterworth
 */
public class MapPaneStyle
{
    /**
     * The image displayed in the background of the MapPane.
     */
    public Drawable background;

    /**
     * Determines the size of the map shown with the MapPane.
     */
    public Vector2 size;

    public Color colour;

    public MapPaneStyle() {
        this.background = null;
        this.size = Zero;
        this.colour = WHITE;
    }

    public MapPaneStyle(MapPaneStyle other) {
        this.background = other.background;
        this.size = other.size;
        this.colour = other.colour;
    }
}
