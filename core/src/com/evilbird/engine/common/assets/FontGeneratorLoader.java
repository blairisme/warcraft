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
