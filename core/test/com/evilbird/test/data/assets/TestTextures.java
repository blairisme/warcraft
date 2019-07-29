/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
