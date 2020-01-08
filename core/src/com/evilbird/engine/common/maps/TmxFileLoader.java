/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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