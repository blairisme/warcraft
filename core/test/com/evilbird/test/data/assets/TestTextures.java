/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.data.assets;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestTextures
{
    private TestTextures() {
    }

    public static Drawable newTestDrawable() {
        TextureRegion region = new TextureRegion(newTestTexture());
        return new TextureRegionDrawable(region);
    }

    public static Texture newTestTexture() {
        return newTestTexture("/data/texture/image.png");
    }

    public static Texture newTestTexture(String path) {
        FileHandle textureFile =  mock(FileHandle.class);
        when(textureFile.path()).thenReturn(path);

        FileTextureData textureData = mock(FileTextureData.class);
        when(textureData.getFileHandle()).thenReturn(textureFile);

        Texture result = mock(Texture.class);
        when(result.getTextureData()).thenReturn(textureData);

        return result;
    }
}
