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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import static java.lang.reflect.Modifier.isTransient;

/**
 * Instances of this class {@link JsonSerializer serialize} and
 * {@link JsonDeserializer deserialize} an object using reflection. Along with
 * and objects fields its class is also included in the serialized output. This
 * makes this class useful for serialization and deserialization of values
 * stored as interfaces.
 *
 * @author Blair Butterworth
 */
public class ReflectionAdapter implements JsonSerializer<Object>, JsonDeserializer<Object>
{
    private static final String TYPE = "_type";
    private static final String VALUE = "_value";

    @Override
    public JsonElement serialize(Object source, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        Class<?> sourceType = source.getClass();
        serializeType(result, sourceType);
        serializeObject(result, context, source, sourceType);
        return result;
    }

    private void serializeType(JsonObject json, Class<?> type) {
        json.addProperty(TYPE, type.getName());
    }

    private void serializeObject(JsonObject json, JsonSerializationContext context, Object target, Class<?> type)  {
        if (type.isEnum()) {
            json.addProperty(VALUE, target.toString());
        } else {
            serializeFields(json, context, target, type);
        }
    }

    private void serializeFields(JsonObject json, JsonSerializationContext context, Object target, Class<?> type) {
        for (Field field : FieldUtils.getAllFields(type)) {
            if (!isTransient(field.getModifiers())) {
                serializeField(json, context, field, target);
            }
        }
    }

    private void serializeField(JsonObject json, JsonSerializationContext context, Field field, Object target) {
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
    public Object deserialize(JsonElement source, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = source.getAsJsonObject();
        Object result = createInstance(json);
        deserializeProperties(json, context, result);
        return result;
    }

    private Object createInstance(JsonObject json) {
        Class<?> type = getType(json);
        if (type.isEnum()) {
            return createEnum(type, json);
        }
        return createObject(type);
    }

    @SuppressWarnings("unchecked")
    private Enum createEnum(Class<?> type, JsonElement json) {
        String name = getValue(json);
        return Enum.valueOf((Class<Enum>)type, name);
    }

    private Object createObject(Class<?> type) {
        try {
            Constructor<?> constructor = type.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        }
        catch (ReflectiveOperationException error) {
            throw new JsonParseException(error);
        }
    }

    private void deserializeProperties(JsonObject json, JsonDeserializationContext context, Object result) {
        for (Entry<String, JsonElement> entry: json.entrySet()) {
            String property = entry.getKey();
            if (!TYPE.equals(property)) {
                deserializeProperty(entry.getKey(), entry.getValue(), context, result, result.getClass());
            }
        }
    }

    private void deserializeProperty(String name, JsonElement json, JsonDeserializationContext context, Object target, Class<?> type) {
        Field field = FieldUtils.getField(type, name, true);
        if (field != null) {
            Object value = deserializeField(json, field.getType(), context);
            writeField(field, target, value);
        }
    }

    private Object deserializeField(JsonElement json, Class<?> type, JsonDeserializationContext context) {
        if (List.class.isAssignableFrom(type)) {
            return deserializeList(json.getAsJsonArray(), context);
        }
        if (Set.class.isAssignableFrom(type)) {
            return deserializeSet(json.getAsJsonArray(), context);
        }
        if (type.isArray()) {
            return deserializeArray(json.getAsJsonArray(), context);
        }
        return context.deserialize(json, type);
    }

    private Set<Object> deserializeSet(JsonArray elements, JsonDeserializationContext context) {
        List<Object> list = deserializeList(elements, context);
        return new HashSet<>(list);
    }

    private Object[] deserializeArray(JsonArray elements, JsonDeserializationContext context) {
        List<Object> list = deserializeList(elements, context);
        return list.toArray();
    }

    private List<Object> deserializeList(JsonArray elements, JsonDeserializationContext context) {
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

    private String getValue(JsonElement json) {
        JsonObject object = json.getAsJsonObject();
        JsonElement value = object.get(VALUE);
        return value.getAsString();
    }
}
