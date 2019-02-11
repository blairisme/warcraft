/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;

public class FreeTypeFontGeneratorLoader extends SynchronousAssetLoader<FreeTypeFontGenerator, FreeTypeFontGeneratorLoaderParameters>
{
    public FreeTypeFontGeneratorLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public FreeTypeFontGenerator load(AssetManager manager, String fileName, FileHandle file, FreeTypeFontGeneratorLoaderParameters parameter) {
        if (file.extension().equals("gen")) {
            file = file.sibling(file.nameWithoutExtension());
        }
        return new FreeTypeFontGenerator(file);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, FreeTypeFontGeneratorLoaderParameters parameter) {
        return null;
    }
}
