/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.evilbird.engine.common.assets.FontGeneratorLoader;
import com.evilbird.engine.common.assets.FontLoader;

/**
 * Instances of this class provide access to game assets.
 *
 * @author Blair Butterworth
 */
public class AndroidAssets extends AssetManager
{
    public AndroidAssets() {
        setLoader(TiledMap.class, new TmxMapLoader(getFileHandleResolver()));
        setLoader(BitmapFont.class, new FontLoader(getFileHandleResolver()));
        setLoader(FreeTypeFontGenerator.class, new FontGeneratorLoader(getFileHandleResolver()));
    }
}
