/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.test.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evilbird.engine.common.assets.LazyMusicLoader;
import com.evilbird.engine.common.assets.SyntheticTexture;
import com.evilbird.engine.common.assets.SyntheticTextureLoader;
import com.evilbird.engine.common.audio.LazyLoadedMusic;

public class TestAssetManager
{
    private TestAssetManager() {
    }

    public static AssetManager getTestAssetManager(FileHandleResolver resolver) {
        AssetManager manager = new AssetManager(resolver);
        manager.setLoader(BitmapFont.class, new MockFontLoader(resolver));
        manager.setLoader(LazyLoadedMusic.class, new LazyMusicLoader(resolver));
        manager.setLoader(SyntheticTexture.class, new SyntheticTextureLoader(resolver));
        return manager;
    }
}
