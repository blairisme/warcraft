/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.maps;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import javax.inject.Inject;

/**
 * Instances of this class load maps created with the Tiled application.
 *
 * @author Blair Butterworth
 */
public class TiledMapLoader
{
    private TmxMapLoader loader;

    @Inject
    public TiledMapLoader() {
        this(new InternalFileHandleResolver());
    }

    public TiledMapLoader(FileHandleResolver resolver) {
        loader = new TmxMapLoader(resolver);
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
