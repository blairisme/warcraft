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
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;

/**
 * Instances of this class load fonts generators, which create Bitmap fonts
 * from TrueType, OTF, and other FreeType supported fonts in memory at runtime.
 *
 * @author Blair Butterworth
 */
public class FontGeneratorLoader
    extends SynchronousAssetLoader<FreeTypeFontGenerator, FontGeneratorLoaderParameters>
{
    public FontGeneratorLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public FreeTypeFontGenerator load(
        AssetManager manager,
        String fileName,
        FileHandle file,
        FontGeneratorLoaderParameters parameter)
    {
        if (file.extension().equals("gen")) {
            file = file.sibling(file.nameWithoutExtension());
        }
        return new FreeTypeFontGenerator(file);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(
        String fileName,
        FileHandle file,
        FontGeneratorLoaderParameters parameter)
    {
        return null;
    }
}
