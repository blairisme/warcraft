/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.assets;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.GridPoint2;

/**
 * Defines the contents of a {@link SyntheticTexture} as loaded by a
 * {@link SyntheticTextureLoader}.
 *
 * @author Blair Butterworth
 */
public class SyntheticTextureParameters extends AssetLoaderParameters<SyntheticTexture>
{
    private GridPoint2 size;
    private Color colour;

    public SyntheticTextureParameters(Color colour, GridPoint2 size) {
        this.size = size;
        this.colour = colour;
    }

    public static SyntheticTextureParameters withColour(Color colour, GridPoint2 size) {
        return new SyntheticTextureParameters(colour, size);
    }

    public GridPoint2 getSize() {
        return size;
    }

    public int getWidth() {
        return size.x;
    }

    public int getHeight() {
        return size.y;
    }

    public Color getColour() {
        return colour;
    }
}
