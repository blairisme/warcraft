/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.maps;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.ImageResolver.AssetManagerImageResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * A {@link TmxMapLoader} extension that allows loading of TMX files using a
 * given {@link AssetManager}. The asset manager will be used to resolve assets
 * referred to in the TMX file.
 *
 * @author Blair Butterworth
 */
public class TmxFileLoader extends TmxMapLoader
{
    private AssetManager assets;

    public TmxFileLoader(AssetManager assets) {
        super(assets.getFileHandleResolver());
        this.assets = assets;
    }

    public TiledMap load(String path) {
        FileHandle file = resolve(path);
        root = xml.parse(file);
        return loadTilemap(root, file, new AssetManagerImageResolver(assets));
    }
}