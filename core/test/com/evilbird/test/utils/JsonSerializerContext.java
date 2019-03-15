/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.utils;

import com.evilbird.engine.common.serialization.JsonSerializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Type;

public class JsonSerializerContext implements JsonSerializationContext, JsonDeserializationContext
{
    private JsonSerializer serializer;

    public JsonSerializerContext() {
        serializer = new JsonSerializer();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(JsonElement json, Type type) throws JsonParseException {
        return serializer.deserializeJson(json, (Class<T>)type);
    }

    @Override
    public JsonElement serialize(Object source) {
        return serialize(source, source.getClass());
    }

    @Override
    public JsonElement serialize(Object source, Type type) {
        return serializer.serializeJson(source, (Class<?>)type);
    }
}