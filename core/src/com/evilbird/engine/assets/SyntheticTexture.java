/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
