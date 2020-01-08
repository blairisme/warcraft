/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.evilbird.engine.assets.FontGeneratorLoader;
import com.evilbird.engine.assets.FontLoader;
import com.evilbird.engine.assets.MusicLoader;
import com.evilbird.engine.assets.SyntheticTexture;
import com.evilbird.engine.assets.SyntheticTextureLoader;
import com.evilbird.engine.audio.music.Music;
import com.evilbird.engine.audio.sound.Sound;

/**
 * Instances of this class provide access to game assets.
 *
 * @author Blair Butterworth
 */
public class AndroidAssets extends AssetManager
{
    public AndroidAssets() {
        setLoader(Music.class, new MusicLoader(getFileHandleResolver()));
        setLoader(Sound.class, new AndroidSoundLoader(getFileHandleResolver()));
        setLoader(TiledMap.class, new TmxMapLoader(getFileHandleResolver()));
        setLoader(BitmapFont.class, new FontLoader(getFileHandleResolver()));
        setLoader(FreeTypeFontGenerator.class, new FontGeneratorLoader(getFileHandleResolver()));
        setLoader(SyntheticTexture.class, new SyntheticTextureLoader(getFileHandleResolver()));
    }
}
