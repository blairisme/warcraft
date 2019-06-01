/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.reflect.TypeRegistry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import javax.inject.Inject;
import java.io.Reader;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Instances of this class serialize objects into their equivalent JSON
 * representation. Methods are provided to convert Java objects into JSON
 * and JSON into Java objects.
 *
 * @author Blair Butterworth
 */
public class JsonSerializer implements Serializer
{
    private Gson gson;

    @Inject
    public JsonSerializer(TypeRegistry typeRegistry) {
        this(typeRegistry, Collections.emptyMap());
    }

    public JsonSerializer(TypeRegistry typeRegistry, Map<Class<?>, Object> adapters) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.serializeSpecialFloatingPointValues();
        gsonBuilder.enableComplexMapKeySerialization();
        gsonBuilder.registerTypeHierarchyAdapter(Predicate.class, new ReflectionAdapter(typeRegistry));
        gsonBuilder.registerTypeHierarchyAdapter(Identifier.class, new ReflectionAdapter(typeRegistry));
        adapters.forEach(gsonBuilder::registerTypeHierarchyAdapter);
        gson = gsonBuilder.create();
    }

    @Override
    public <T> String serialize(T value, Class<T> type) throws SerializationException {
        try {
            return gson.toJson(value, type);
        }
        catch (Throwable error) {
            throw new SerializationException(error);
        }
    }

    @Override
    public <T> void serialize(T value, Class<T> type, Writer writer) throws SerializationException {
        try {
            gson.toJson(value, type, writer);
        }
        catch (Throwable error) {
            throw new SerializationException(error);
        }
    }

    public JsonElement serializeJson(Object value, Class<?> type) throws SerializationException {
        try {
            return gson.toJsonTree(value, type);
        }
        catch (Throwable error) {
            throw new SerializationException(error);
        }
    }

    @Override
    public <T> T deserialize(String value, Class<T> type) throws SerializationException {
        try {
            return gson.fromJson(value, type);
        }
        catch (Throwable error) {
            throw new SerializationException(error);
        }
    }

    @Override
    public <T> T deserialize(Reader reader, Class<T> type) throws SerializationException {
        try {
            return gson.fromJson(reader, type);
        }
        catch (Throwable error) {
            throw new SerializationException(error);
        }
    }

    public <T> T deserializeJson(Object element, Class<T> type) throws SerializationException {
        try {
            return gson.fromJson((JsonElement)element, type);
        }
        catch (Throwable error) {
            throw new SerializationException(error);
        }
    }
}
