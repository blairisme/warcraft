/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.audio.sound.Sound;

/**
 * Loads sound assets using a wrapper which provides supplementary behaviour to
 * the LibGDX Sound class.
 *
 * @author Blair Butterworth
 */
public abstract class SoundLoader extends SynchronousAssetLoader<Sound, SoundParameters>
{
    public SoundLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public Sound load(AssetManager assets, String name, FileHandle file, SoundParameters parameter) {
        return newSound(assets.getFileHandleResolver(), name);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String name, FileHandle file, SoundParameters parameter) {
        return null;
    }

    protected abstract Sound newSound(FileHandleResolver resolver, String path);
}
