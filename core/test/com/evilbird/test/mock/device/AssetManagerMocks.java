/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.mock.device;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AssetManagerMocks
{
    public static AssetManager newAssetManagerMock() {
        Texture mockTexture = TextureMocks.newTextureMock();

        AssetManager assets = mock(AssetManager.class);
        when(assets.get(anyString(), eq(Texture.class))).thenReturn(mockTexture);
        when(assets.get(anyString(), eq(Sound.class))).thenReturn(mock(Sound.class));

        return assets;
    }
}
