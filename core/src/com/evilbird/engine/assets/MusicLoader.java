/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.audio.music.Music;
import com.evilbird.engine.audio.music.MusicFile;

/**
 * Loads music assets using a wrapper which will only load music data into
 * memory when the music is played.
 *
 * @author Blair Butterworth
 */
public class MusicLoader extends SynchronousAssetLoader<Music, MusicParameters>
{
    public MusicLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public Music load(AssetManager assets, String name, FileHandle file, MusicParameters parameter) {
        return new MusicFile(assets.getFileHandleResolver(), name);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String name, FileHandle file, MusicParameters parameter) {
        return null;
    }
}
