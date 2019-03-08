/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.google.gson.*;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Instances of this class {@link JsonSerializer serialize} and
 * {@link JsonDeserializer deserialize} an object using reflection. Along with
 * an objects fields its class is also included in the serialized output. This
 * makes this class useful for serialization and deserialization of values
 * stored as interfaces.
 *
 * @author Blair Butterworth
 */
public class ReflectionAdapter extends AbstractAdapter<Object>
{
    private static final String CLASS = "class";
    private static final String ENUM = "enum";

    @Override
    protected JsonObject getSerializedType(Object target, JsonSerializationContext context) {
        Class<?> clazz = target.getClass();
        String type = getTypeName(clazz);

        if (clazz.isEnum()) {
            return getEnumType(target, type);
        }
        return getObjectType(type);
    }

    private String getTypeName(Class<?> clazz) {
        if (SerializedTypes.hasAlias(clazz)) {
            return SerializedTypes.getAlias(clazz);
        }
        return clazz.getName();
    }

    private JsonObject getEnumType(Object target, String type) {
        JsonObject result = new JsonObject();
        result.addProperty(CLASS, type);
        result.addProperty(ENUM, target.toString());
        return result;
    }

    private JsonObject getObjectType(String type) {
        JsonObject result = new JsonObject();
        result.addProperty(CLASS, type);
        return result;
    }

    @Override
    protected boolean isSerializedField(Object target, Field field) {
        return !Modifier.isTransient(field.getModifiers());
    }

    @Override
    protected Class<?> getDeserializedType(JsonObject json, JsonDeserializationContext context) {
        return getClass(json);
    }

    @Override
    protected Object getDeserializedInstance(JsonObject json, JsonDeserializationContext context) {
        if (json.has(ENUM)) {
            return getEnumInstance(json);
        }
        return getObjectInstance(json);
    }

    @SuppressWarnings("unchecked")
    private Object getEnumInstance(JsonObject json) {
        Class<Enum> type = (Class<Enum>)getClass(json);
        String value = json.get(ENUM).getAsString();
        return Enum.valueOf(type, value);
    }

    private Object getObjectInstance(JsonObject json) {
        Class<?> type = getClass(json);
        return ReflectionUtils.getInstance(type);
    }

    private Class<?> getClass(JsonObject json) {
        try {
            String name = json.get(CLASS).getAsString();
            if (SerializedTypes.hasType(name)) {
                return SerializedTypes.getType(name);
            }
            return Class.forName(name);
        }
        catch (ReflectiveOperationException error) {
            throw new JsonParseException(error);
        }
    }

    @Override
    protected boolean isDeserializedField(String name, JsonElement value) {
        return true;
    }
}
