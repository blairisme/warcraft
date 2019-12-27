/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.test.utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.assets.FontLoaderParameters;
import com.evilbird.engine.common.collection.Arrays;
import com.evilbird.engine.common.text.Fonts;

/**
 * A mock font loader implementation, which doesn't require loading native
 * libraries.
 *
 * @author Blair Butterworth
 */
public class MockFontLoader extends SynchronousAssetLoader<BitmapFont, FontLoaderParameters>
{
    public MockFontLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public BitmapFont load(AssetManager manager, String name, FileHandle file, FontLoaderParameters parameter) {
        return Fonts.ARIAL;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String name, FileHandle file, FontLoaderParameters parameter) {
        return Arrays.emptyArray();
    }
}