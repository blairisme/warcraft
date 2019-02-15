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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Array;

public class FreeTypeFontLoader extends SynchronousAssetLoader<BitmapFont, FreeTypeFontLoaderParameters> {

    public FreeTypeFontLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public BitmapFont load(AssetManager manager, String fileName, FileHandle file, FreeTypeFontLoaderParameters parameters) {
        FreeTypeFontParameter style = getStyle(parameters);
        FreeTypeFontGenerator generator = manager.get(fileName + ".gen", FreeTypeFontGenerator.class);
        generator.scaleForPixelHeight(style.size);
        return generator.generateFont(style);
    }

    private FreeTypeFontParameter getStyle(FreeTypeFontLoaderParameters parameters) {
        if (parameters != null) {
            return parameters.getFontStyle();
        }
        return new FreeTypeFontGenerator.FreeTypeFontParameter();
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, FreeTypeFontLoaderParameters parameters) {
        Array<AssetDescriptor> result = new Array<>();
        result.add(new AssetDescriptor<>(fileName + ".gen", FreeTypeFontGenerator.class));
        return result;
    }
}