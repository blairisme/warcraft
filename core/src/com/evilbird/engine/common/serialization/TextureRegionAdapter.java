/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.*;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class TextureRegionAdapter implements JsonSerializer<TextureRegion>, JsonDeserializer<TextureRegion>
{
    @Override
    public JsonElement serialize(TextureRegion source, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("texture", context.serialize(source.getTexture(), Texture.class));
        result.addProperty("width", source.getRegionWidth());
        result.addProperty("height", source.getRegionHeight());
        return result;
    }

    @Override
    public TextureRegion deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        Texture texture = context.deserialize(object.get("texture"), Texture.class);
        int width = object.get("width").getAsInt();
        int height = object.get("height").getAsInt();
        return new TextureRegion(texture, width, height);
    }
}
