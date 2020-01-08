/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.assets;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.GridPoint2;

import static com.evilbird.engine.assets.SyntheticTextureType.Outline;

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
    private SyntheticTextureType type;

    public SyntheticTextureParameters(Color colour, GridPoint2 size, SyntheticTextureType type) {
        this.size = size;
        this.colour = colour;
        this.type = type;
    }

    public static SyntheticTextureParameters withColour(Color colour, GridPoint2 size) {
        return new SyntheticTextureParameters(colour, size, Outline);
    }

    public static SyntheticTextureParameters with(Color colour, GridPoint2 size, SyntheticTextureType type) {
        return new SyntheticTextureParameters(colour, size, type);
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

    public SyntheticTextureType getType() {
        return type;
    }
}
