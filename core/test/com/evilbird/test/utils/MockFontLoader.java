/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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