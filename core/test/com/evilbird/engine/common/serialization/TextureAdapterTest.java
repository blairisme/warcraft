/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.test.common.GameTestCase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextureAdapterTest extends GameTestCase
{
    private Gson serializer;
    private AssetManager assetManager;

    public TextureAdapterTest() {
        assetManager = mock(AssetManager.class);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Texture.class, new TextureAdapter(assetManager));
        serializer = gsonBuilder.create();
    }

    @Test
    public void serializeTest() {
        FileHandle fileHandle = Gdx.files.classpath("texture.png");
        Texture texture = new Texture(fileHandle);

        String expected = "{\"file\":\"texture.png\"}";
        String actual = serializer.toJson(texture);

        Assert.assertEquals(expected, actual);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void serializeNonFileTest() {
        Texture texture = new Texture(5, 5, Pixmap.Format.RGB888);
        serializer.toJson(texture);
    }

    @Test
    public void deserializeTest() {
        Texture expected = mock(Texture.class);
        when(assetManager.get("texture.png", Texture.class)).thenReturn(expected);

        String json = "{\"file\":\"texture.png\"}";
        Texture actual = serializer.fromJson(json, Texture.class);

        Assert.assertEquals(expected, actual);
    }
}