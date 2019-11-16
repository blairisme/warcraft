/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.common.text.Fonts;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestAssets
{
    private TestAssets() {
    }

    public static AssetManager newAssetManagerMock() {
        Texture mockTexture = TestTextures.newTestTexture();
        BitmapFont mockFont = Fonts.ARIAL;
        I18NBundle mockBundle = TestStringBundles.newTestBundle();

        AssetManager assets = mock(AssetManager.class);
        when(assets.get(anyString(), eq(Texture.class))).thenReturn(mockTexture);
        when(assets.get(anyString(), eq(Sound.class))).thenReturn(mock(Sound.class));
        when(assets.get(anyString(), eq(Music.class))).thenReturn(mock(Music.class));
        when(assets.get(anyString(), eq(BitmapFont.class))).thenReturn(mockFont);
        when(assets.get(anyString(), eq(I18NBundle.class))).thenReturn(mockBundle);

        return assets;
    }
}
