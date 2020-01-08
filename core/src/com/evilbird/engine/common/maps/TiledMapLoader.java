/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.maps;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameService;

import javax.inject.Inject;

/**
 * Instances of this class load maps created with the Tiled application.
 *
 * @author Blair Butterworth
 */
public class TiledMapLoader
{
    private TmxMapLoader loader;

    public TiledMapLoader() {
        this(GameService.getInstance().getDevice());
    }

    @Inject
    public TiledMapLoader(Device device) {
        this(device.getAssetStorage());
    }

    public TiledMapLoader(AssetManager assets) {
        this.loader = new TmxFileLoader(assets);
    }

    /**
     * Loads the {@link TiledMapFile} from the given file. The file is resolved
     * via the {@link FileHandleResolver} set in the constructor of this class.
     *
     * @param path  the path to a file. This parameter cannot be <code>null</code>.
     * @return      a {@link TiledMapFile}.
     */
    public TiledMapFile load(String path) {
        TiledMap map = loader.load(path);
        return new TiledMapFile(map, path);
    }
}
