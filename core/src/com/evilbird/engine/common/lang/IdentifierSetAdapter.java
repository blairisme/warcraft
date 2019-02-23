/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import com.google.gson.*;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Instances of this class serialize and deserialize {@link IdentifierSet
 * IdentifierSets}.
 *
 * @author Blair Butterworth
 */
public class IdentifierSetAdapter implements JsonSerializer<IdentifierSet>, JsonDeserializer<IdentifierSet>
{
    @Override
    public JsonElement serialize(IdentifierSet source, Type type, JsonSerializationContext context) {
        JsonArray result = new JsonArray();
        for (Identifier identifier: source) {
            result.add(context.serialize(identifier, Identifier.class));
        }
        return result;
    }

    @Override
    public IdentifierSet deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        IdentifierSet result = new IdentifierSet();
        for (JsonElement element: json.getAsJsonArray()) {
            result.add(context.deserialize(element, Identifier.class));
        }
        return result;
    }
}
