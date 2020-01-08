/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
