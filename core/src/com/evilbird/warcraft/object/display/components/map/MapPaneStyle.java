/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
