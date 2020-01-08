/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.utils;

import com.evilbird.engine.common.serialization.JsonSerializer;
import com.evilbird.warcraft.type.WarcraftTypeRegistry;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Type;

public class JsonSerializerContext implements JsonSerializationContext, JsonDeserializationContext
{
    private JsonSerializer serializer;

    public JsonSerializerContext() {
        serializer = new JsonSerializer(new WarcraftTypeRegistry());
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