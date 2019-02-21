/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.google.gson.*;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class TextureAdapter implements JsonSerializer<Texture>, JsonDeserializer<Texture>
{
    private static final String FILE_PATH = "file";
    private AssetManager assets;

    public TextureAdapter(AssetManager assets) {
        this.assets = assets;
    }

    @Override
    public JsonElement serialize(Texture source, Type type, JsonSerializationContext context) {
        TextureData textureData = source.getTextureData();
        if (textureData instanceof FileTextureData) {
            return serializeFileTexture((FileTextureData)textureData);
        }
        throw new UnsupportedOperationException();
    }

    private JsonElement serializeFileTexture(FileTextureData textureData) {
        FileHandle fileHandle = textureData.getFileHandle();
        JsonObject result = new JsonObject();
        result.addProperty(FILE_PATH, fileHandle.path());
        return result;
    }

    @Override
    public Texture deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement element = jsonObject.get(FILE_PATH);
        String path = element.getAsString();

        assets.load(path, Texture.class);
        assets.finishLoadingAsset(path);
        return assets.get(path, Texture.class);

    }
}
