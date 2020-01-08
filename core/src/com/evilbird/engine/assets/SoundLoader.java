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
