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
import com.evilbird.engine.assets.MusicLoader;
import com.evilbird.engine.assets.SyntheticTexture;
import com.evilbird.engine.assets.SyntheticTextureLoader;
import com.evilbird.engine.audio.music.Music;
import com.evilbird.engine.audio.sound.Sound;

public class TestAssetManager
{
    private TestAssetManager() {
    }

    public static AssetManager getTestAssetManager(FileHandleResolver resolver) {
        AssetManager manager = new AssetManager(resolver);
        manager.setLoader(BitmapFont.class, new MockFontLoader(resolver));
        manager.setLoader(Music.class, new MusicLoader(resolver));
        manager.setLoader(Sound.class, new MockSoundLoader(resolver));
        manager.setLoader(SyntheticTexture.class, new SyntheticTextureLoader(resolver));
        return manager;
    }
}
