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
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Map.Entry;

/**
 * Instances of this class {@link JsonSerializer serialize} and
 * {@link JsonDeserializer deserialize} an object using reflection. Along with
 * an objects fields its class is also included in the serialized output. This
 * makes this class useful for serialization and deserialization of values
 * stored as interfaces.
 *
 * @param <T> the type of object serialized by the AbstractAdapter.
 *
 * @author Blair Butterworth
 */
public abstract class AbstractAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T>
{
    @Override
    public JsonElement serialize(T source, Type type, JsonSerializationContext context) {
        JsonSerializer<T> annotatedSerializer = getAnnotatedSerializer(source.getClass());
        if (annotatedSerializer != null) {
            return annotatedSerializer.serialize(source, type, context);
        }
        return serialize(source, context);
    }

    private JsonElement serialize(T source, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        serializeType(result, source, context);
        serializeFields(result, context, source);
        return result;
    }

    private void serializeType(JsonObject json, T target, JsonSerializationContext context) {
        JsonObject type = getSerializedType(target, context);
        if (type != null) {
            for (Entry<String, JsonElement> entry: type.entrySet()) {
                json.add(entry.getKey(), entry.getValue());
            }
        }
    }

    protected abstract JsonObject getSerializedType(T target, JsonSerializationContext context);

    private void serializeFields(JsonObject json, JsonSerializationContext context, T target) {
        Class<?> type = target.getClass();
        for (Field field : getSerializedFields(type)) {
            if (! field.isSynthetic() && isSerializedField(target, field)) {
                serializeField(json, context, target, field);
            }
        }
    }

    private Field[] getSerializedFields(Class<?> type) {
        if (type.isEnum()) {
            return new Field[0];
        }
        return FieldUtils.getAllFields(type);
    }

    protected abstract boolean isSerializedField(T target, Field field);

    private void serializeField(JsonObject json, JsonSerializationContext context, Object target, Field field) {
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
    public T deserialize(JsonElement source, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = source.getAsJsonObject();
        JsonDeserializer<T> annotatedDeserializer = getAnnotatedDeserializer(getDeserializedType(json, context));
        if (annotatedDeserializer != null) {
            return annotatedDeserializer.deserialize(json, type, context);
        }
        return deserialize(json, context);
    }

    public T deserialize(JsonObject json, JsonDeserializationContext context) throws JsonParseException {
        T result = getDeserializedInstance(json, context);
        deserializeProperties(json, context, result);
        invokeAnnotatedInitializer(result);
        return result;
    }

    protected abstract Class<?> getDeserializedType(JsonObject json, JsonDeserializationContext context);

    protected abstract T getDeserializedInstance(JsonObject json, JsonDeserializationContext context);

    private void deserializeProperties(JsonObject json, JsonDeserializationContext context, Object result) {
        Class<?> type = result.getClass();
        for (Entry<String, JsonElement> entry: json.entrySet()) {
            String name = entry.getKey();
            JsonElement value = entry.getValue();

            if (isDeserializedField(name, value)) {
                deserializeProperty(name, value, context, result, type);
            }
        }
    }

    protected abstract boolean isDeserializedField(String name, JsonElement value);

    private void deserializeProperty(String name, JsonElement json, JsonDeserializationContext context, Object target, Class<?> type) {
        Field field = FieldUtils.getField(type, name, true);
        if (field != null) {
            Object value = deserializeField(json, context, field);
            writeField(field, target, value);
        }
    }

    @SuppressWarnings("unchecked")
    private Object deserializeField(JsonElement json, JsonDeserializationContext context, Field field) {
        Class<?> fieldType = field.getType();
        if (List.class.isAssignableFrom(fieldType)) {
            return deserializeList(json.getAsJsonArray(), context, field);
        }
        if (Map.class.isAssignableFrom(fieldType)) {
            return context.deserialize(json, LinkedHashMap.class);
        }
        if (Set.class.isAssignableFrom(fieldType) ) {
            return deserializeSet(json.getAsJsonArray(), context, field);
        }
        if (Collection.class.isAssignableFrom(fieldType)) {
            return deserializeList(json.getAsJsonArray(), context, field);
        }
        if (fieldType.isArray()) {
            return deserializeArray(json.getAsJsonArray(), context, field);
        }
        return context.deserialize(json, fieldType);
    }

    private Set<Object> deserializeSet(JsonArray elements, JsonDeserializationContext context, Field field) {
        List<Object> list = deserializeList(elements, context, field);
        return new HashSet<>(list);
    }

    private Object[] deserializeArray(JsonArray elements, JsonDeserializationContext context, Field field) {
        List<Object> list = deserializeList(elements, context, field);
        return list.toArray();
    }

    private List<Object> deserializeList(JsonArray elements, JsonDeserializationContext context, Field field) {
        Class<?> type = getListGenericType(field);
        List<Object> result = new ArrayList<>();
        for (JsonElement element: elements) {
            result.add(context.deserialize(element, type));
        }
        return result;
    }

    private Class<?> getListGenericType(Field field) {
        ParameterizedType parameterizedType = (ParameterizedType)field.getGenericType();
        if (parameterizedType != null) {
            return (Class<?>)parameterizedType.getActualTypeArguments()[0];
        }
        return Object.class;
    }

    private void writeField(Field field, Object target, Object value) {
        try {
            FieldUtils.writeField(field, target, value, true);
        }
        catch (IllegalAccessException error) {
            throw new JsonParseException(error);
        }
    }

    @SuppressWarnings("unchecked")
    private JsonSerializer<T> getAnnotatedSerializer(Class<?> type) {
        Object annotatedAdapter = getAnnotatedAdapter(type);
        if (annotatedAdapter instanceof JsonSerializer){
            return (JsonSerializer<T>)annotatedAdapter;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private JsonDeserializer<T> getAnnotatedDeserializer(Class<?> type) {
        Object annotatedAdapter = getAnnotatedAdapter(type);
        if (annotatedAdapter instanceof JsonDeserializer){
            return (JsonDeserializer<T>)annotatedAdapter;
        }
        return null;
    }

    private Object getAnnotatedAdapter(Class<?> type) {
        if (type.isAnnotationPresent(JsonAdapter.class)){
            JsonAdapter annotation = type.getAnnotation(JsonAdapter.class);
            if (annotation.value() != this.getClass()) {
                Class<?> adapterType = annotation.value();
                return ReflectionUtils.getInstance(adapterType);
            }
        }
        return null;
    }

    private void invokeAnnotatedInitializer(Object object) {
        ReflectionUtils.invokeMethod(object, SerializedInitializer.class);
    }
}
