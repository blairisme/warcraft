/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.evilbird.engine.common.audio.SoundType;

public class AssetUtilities
{
    public static void loadSet(AssetManager assets, String prefix, String suffix, int count, Class<?> type) {
        for (int i = 1; i <= count; i++){
            assets.load(prefix + i + suffix, type);
        }
    }

    public static void loadSoundSet(AssetManager assets, String prefix, SoundType type, int count) {
        loadSet(assets, prefix, type.getFileExtension(), count, Sound.class);
    }

    public static FreeTypeFontLoaderParameters fontSize(int size) {
        FreeTypeFontParameter style = new FreeTypeFontParameter();
        style.size = size;
        return new FreeTypeFontLoaderParameters(style);
    }
}
