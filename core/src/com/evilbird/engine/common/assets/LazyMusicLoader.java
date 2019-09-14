/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.audio.music.LazyLoadedMusic;

/**
 * Loads music assets using a wrapper which will only load music data into
 * memory when the music is played.
 *
 * @author Blair Butterworth
 */
public class LazyMusicLoader extends SynchronousAssetLoader<LazyLoadedMusic, LazyMusicParameters>
{
    public LazyMusicLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public LazyLoadedMusic load(AssetManager assets, String name, FileHandle file, LazyMusicParameters parameter) {
        return new LazyLoadedMusic(assets.getFileHandleResolver(), name);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String name, FileHandle file, LazyMusicParameters parameter) {
        return null;
    }
}
