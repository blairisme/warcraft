/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.serialization;

import com.evilbird.engine.common.reflect.TypeRegistry;
import com.evilbird.engine.game.GameService;
import com.google.gson.JsonSerializer;
import com.google.gson.*;

import javax.inject.Inject;
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

    private TypeRegistry typeRegistry;

    @Inject
    public ReflectionAdapter(TypeRegistry typeRegistry) {
        this.typeRegistry = typeRegistry;
    }

    private ReflectionAdapter() {
        GameService service = GameService.getInstance();
        this.typeRegistry = service.getTypeRegistry();
    }

    @Override
    protected JsonObject getSerializedType(Object target, JsonSerializationContext context) {
        Class<?> clazz = target.getClass();
        String type = typeRegistry.getName(clazz);

        if (clazz.isEnum()) {
            return getEnumType(target, type);
        }
        return getObjectType(type);
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
        String name = json.get(CLASS).getAsString();
        return typeRegistry.getType(name);
    }

    @Override
    protected boolean isDeserializedField(String name, JsonElement value) {
        return true;
    }
}
