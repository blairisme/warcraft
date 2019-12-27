/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.assets;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

/**
 * Represents a synthetically constructed texture.
 *
 * @author Blair Butterworth
 */
public class SyntheticTexture extends Texture
{
    public SyntheticTexture(Pixmap pixmap) {
        super(pixmap);
    }
}
