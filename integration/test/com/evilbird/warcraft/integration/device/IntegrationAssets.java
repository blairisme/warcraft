/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.integration.device;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.evilbird.engine.common.assets.FontGeneratorLoader;
import com.evilbird.engine.common.assets.FontLoader;
import com.evilbird.engine.common.assets.LazyMusicLoader;
import com.evilbird.engine.common.assets.SyntheticTexture;
import com.evilbird.engine.common.assets.SyntheticTextureLoader;
import com.evilbird.engine.common.audio.LazyLoadedMusic;

/**
 * Instances of this class provide access to game assets.
 *
 * @author Blair Butterworth
 */
public class IntegrationAssets extends AssetManager
{
    public IntegrationAssets() {
        super(new IntegrationFileHandleResolver());
        setLoader(TiledMap.class, new TmxMapLoader(getFileHandleResolver()));
        setLoader(BitmapFont.class, new FontLoader(getFileHandleResolver()));
        setLoader(FreeTypeFontGenerator.class, new FontGeneratorLoader(getFileHandleResolver()));
        setLoader(LazyLoadedMusic.class, new LazyMusicLoader(getFileHandleResolver()));
        setLoader(SyntheticTexture.class, new SyntheticTextureLoader(getFileHandleResolver()));
    }

    @Override
    public synchronized <T> T get(String fileName) {
        IntegrationFileHandleResolver resolver = (IntegrationFileHandleResolver)getFileHandleResolver();
        return super.get(resolver.relativize(fileName));
    }

    @Override
    public synchronized <T> T get(String fileName, Class<T> type) {
        IntegrationFileHandleResolver resolver = (IntegrationFileHandleResolver)getFileHandleResolver();
        return super.get(resolver.relativize(fileName), type);
    }

    @Override
    public synchronized <T> T get(AssetDescriptor<T> asset) {
        IntegrationFileHandleResolver resolver = (IntegrationFileHandleResolver)getFileHandleResolver();
        AssetDescriptor<T> descriptor = new AssetDescriptor<T>(
            resolver.relativize(asset.fileName), asset.type, asset.params);
        return super.get(descriptor);
    }
}
