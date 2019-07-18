/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.assets;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

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
