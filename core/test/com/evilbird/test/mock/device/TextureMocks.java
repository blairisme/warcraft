/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.mock.device;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextureMocks
{
    public static Texture newTextureMock() {
        return newTextureMock("/data/texture/image.png");
    }

    public static Texture newTextureMock(String path) {
        FileHandle textureFile =  mock(FileHandle.class);
        when(textureFile.path()).thenReturn(path);

        FileTextureData textureData = mock(FileTextureData.class);
        when(textureData.getFileHandle()).thenReturn(textureFile);

        Texture result = mock(Texture.class);
        when(result.getTextureData()).thenReturn(textureData);

        return result;
    }
}
