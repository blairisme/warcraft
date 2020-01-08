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
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;

import java.util.Objects;

import static com.evilbird.engine.assets.SyntheticTextureType.Filled;
import static com.evilbird.engine.assets.SyntheticTextureType.Outline;

/**
 * Generates a {@link SyntheticTexture} whose contents are defined by a given
 * {@link SyntheticTextureParameters}.
 *
 * @author Blair Butterworth
 */
public class SyntheticTextureLoader extends SynchronousAssetLoader<SyntheticTexture, SyntheticTextureParameters>
{
    public SyntheticTextureLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public SyntheticTexture load(AssetManager manager, String name, FileHandle file, SyntheticTextureParameters para){
        Objects.requireNonNull(para);
        Pixmap pixmap = new Pixmap(para.getWidth(), para.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(para.getColour());
        drawContents(pixmap, para.getType());
        return new SyntheticTexture(pixmap);
    }

    private void drawContents(Pixmap pixmap, SyntheticTextureType type) {
        if (type == Outline) {
            pixmap.drawRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
        }
        if (type == Filled) {
            pixmap.fillRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
        }
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String name, FileHandle file, SyntheticTextureParameters para) {
        return null;
    }
}
