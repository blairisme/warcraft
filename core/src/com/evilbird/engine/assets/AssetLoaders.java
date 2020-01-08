/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evilbird.engine.audio.sound.Sound;

import static com.evilbird.engine.common.file.FileType.MP3;
import static com.evilbird.engine.common.file.FileType.PNG;
import static com.evilbird.engine.common.file.FileType.TTF;

/**
 * Provides utility functions that operate on asset loaders.
 *
 * @author Blair Butterworth
 */
public class AssetLoaders
{
    private AssetLoaders() {
    }

    public static Class<?> getAssetLoader(String path) {
        if (path.endsWith(MP3.getFileExtension())) {
            return Sound.class;
        }
        if (path.endsWith(PNG.getFileExtension())) {
            return Texture.class;
        }
        if (path.endsWith(TTF.getFileExtension())) {
            return BitmapFont.class;
        }
        throw new UnsupportedOperationException("Unknown asset loader for: " + path);
    }
}
