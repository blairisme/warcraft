/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.google.gson.JsonSerializer;
import com.google.gson.*;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import static java.lang.reflect.Modifier.isTransient;

public class ReflectionAdapter implements JsonSerializer<Object>, JsonDeserializer<Object>
{
    private static final String TYPE = "type";

    @Override
    public JsonElement serialize(Object object, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty(TYPE, object.getClass().getName());

        for (Field field: FieldUtils.getAllFields(object.getClass())) {
            if (! isTransient(field.getModifiers())) {
                serialize(result, context, field, object);
            }
        }
        return result;
    }

    private void serialize(JsonObject json, JsonSerializationContext context, Field field, Object target) {
        String name = field.getName();
        Class<?> type = field.getType();
        Object value = readField(field, target);
        json.add(name, context.serialize(value, type));
    }

    private Object readField(Field field, Object target) {
        try {
            return FieldUtils.readField(field, target, true);
        }
        catch (IllegalAccessException error) {
            throw new JsonParseException(error);
        }
    }

    @Override
    public Object deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Object result = createInstance(jsonObject);

        for (Entry<String, JsonElement> entry: jsonObject.entrySet()) {
            deserialize(entry.getKey(), entry.getValue(), context, result, result.getClass());
        }
        return result;
    }

    private Object createInstance(JsonObject json) {
        try {
            Class<?> type = getType(json);
            return ConstructorUtils.invokeConstructor(type);
        }
        catch (ReflectiveOperationException error) {
            throw new JsonParseException(error);
        }
    }

    private void deserialize(String name, JsonElement json, JsonDeserializationContext context, Object target, Class<?> type) {
        Field field = FieldUtils.getField(type, name, true);
        if (field != null) {
            Object value = deserialize(json, field.getType(), context);
            writeField(field, target, value);
        }
    }

    private Object deserialize(JsonElement json, Class<?> type, JsonDeserializationContext context) {
        if (List.class.isAssignableFrom(type)) {
            return deserializeArray(json.getAsJsonArray(), context);
        }
        if (Set.class.isAssignableFrom(type)) {
            return new HashSet<>(deserializeArray(json.getAsJsonArray(), context));
        }
        if (type.isArray()) {
            return deserializeArray(json.getAsJsonArray(), context).toArray();
        }
        return context.deserialize(json, type);
    }

    private List<Object> deserializeArray(JsonArray elements, JsonDeserializationContext context) {
        List<Object> result = new ArrayList<>();
        for (JsonElement element: elements) {
            Class<?> type = getType(element);
            result.add(context.deserialize(element, type));
        }
        return result;
    }

    private void writeField(Field field, Object target, Object value) {
        try {
            FieldUtils.writeField(field, target, value, true);
        }
        catch (IllegalAccessException error) {
            throw new JsonParseException(error);
        }
    }

    private Class<?> getType(JsonElement json){
        try {
            JsonObject object = json.getAsJsonObject();
            JsonElement element = object.get(TYPE);
            String name = element.getAsString();
            return Class.forName(name);
        }
        catch (ReflectiveOperationException error) {
            throw new JsonParseException(error);
        }
    }
}
