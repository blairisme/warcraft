/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.common.value;

import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javax.inject.Inject;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Serializes and deserializes {@link Value Values}.
 *
 * @author Blair Butterworth
 */
public class ValueSerializer implements JsonSerializer<Value>, JsonDeserializer<Value>
{
    private static final String BUFF = "buff";
    private static final String ABSOLUTE = "absolute";
    private static final String ORIGINAL = "original";

    @Inject
    public ValueSerializer() {
    }

    @Override
    public JsonElement serialize(Value source, Type type, JsonSerializationContext context) {
        if (source instanceof BuffValue) {
            return serializeBuff((BuffValue)source, context);
        }
        if (source instanceof AbsoluteBuffValue) {
            return serializeAbsolute((AbsoluteBuffValue)source, context);
        }
        if (source instanceof FixedValue) {
            return serializeFixed((FixedValue)source);
        }
        if (source instanceof UpgradeValue) {
            return serializeUpgrade((UpgradeValue)source);
        }
        throw new UnsupportedOperationException();
    }

    private JsonElement serializeBuff(BuffValue source, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty(BUFF, source.getModifier());
        json.add(ORIGINAL, context.serialize(source.getOriginal()));
        return json;
    }

    private JsonElement serializeAbsolute(AbsoluteBuffValue source, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty(ABSOLUTE, source.getModified());
        json.add(ORIGINAL, context.serialize(source.getOriginal()));
        return json;
    }

    private JsonElement serializeFixed(FixedValue source) {
        return new JsonPrimitive(source.getValue());
    }

    private JsonElement serializeUpgrade(UpgradeValue source) {
        JsonObject json = new JsonObject();
        json.addProperty(Upgrade.None.name(), source.base);
        for (Entry<Upgrade, Float> entry: source.upgrades.entrySet()) {
            json.addProperty(entry.getKey().name(), entry.getValue());
        }
        return json;
    }

    @Override
    public Value deserialize(JsonElement source, Type type, JsonDeserializationContext context) {
        if (source.isJsonPrimitive()) {
            return deserializeFixed(source);
        }
        JsonObject json = source.getAsJsonObject();

        if (json.has(BUFF)) {
            return deserializeBuff(json, context);
        }
        if (json.has(ABSOLUTE)) {
            return deserializeAbsolute(json, context);
        }
        if (json.has(Upgrade.None.name())) {
            return deserializeUpgrade(json);
        }
        throw new UnsupportedOperationException();
    }

    private Value deserializeBuff(JsonObject json, JsonDeserializationContext context) {
        float buff = json.get(BUFF).getAsFloat();
        Value original = context.deserialize(json.get(ORIGINAL), Value.class);
        return new BuffValue(original, buff);
    }

    private Value deserializeAbsolute(JsonObject json, JsonDeserializationContext context) {
        float buff = json.get(ABSOLUTE).getAsFloat();
        Value original = context.deserialize(json.get(ORIGINAL), Value.class);
        return new AbsoluteBuffValue(buff, original);
    }

    private Value deserializeFixed(JsonElement json) {
        float value = json.getAsFloat();
        return new FixedValue(value);
    }

    private Value deserializeUpgrade(JsonObject json) {
        Map<Upgrade, Float> upgrades = new HashMap<>();
        for (String key: json.keySet()) {
            JsonElement element = json.get(key);
            Upgrade upgrade = Upgrade.valueOf(key);
            Float value = element.getAsFloat();
            upgrades.put(upgrade, value);
        }
        return new UpgradeValue(upgrades.remove(Upgrade.None), upgrades);
    }
}
