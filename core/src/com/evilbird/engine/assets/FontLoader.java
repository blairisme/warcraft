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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Array;

/**
 * Instances of this class load fonts from TrueType, OTF, and other FreeType
 * supported fonts.
 *
 * @author Blair Butterworth
 */
public class FontLoader extends SynchronousAssetLoader<BitmapFont, FontLoaderParameters> {

    public FontLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public BitmapFont load(AssetManager manager, String fileName, FileHandle file, FontLoaderParameters parameters) {
        FreeTypeFontParameter style = getStyle(parameters);
        FreeTypeFontGenerator generator = manager.get(fileName + ".gen", FreeTypeFontGenerator.class);
        return generator.generateFont(style);
    }

    private FreeTypeFontParameter getStyle(FontLoaderParameters parameters) {
        if (parameters != null) {
            return parameters.getFontStyle();
        }
        return new FreeTypeFontGenerator.FreeTypeFontParameter();
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, FontLoaderParameters parameters) {
        Array<AssetDescriptor> result = new Array<>();
        result.add(new AssetDescriptor<>(fileName + ".gen", FreeTypeFontGenerator.class));
        return result;
    }
}