/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object;

import com.evilbird.engine.common.reflect.TypeRegistry;
import com.evilbird.engine.common.serialization.AbstractAdapter;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.engine.game.GameService;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

/**
 * Instances of this class serialize and deserialize {@link GameObject Items}. New
 * new object instances are created using the {@link GameObjectFactory} and the type
 * field in the given Java or Json data.
 *
 * @author Blair Butterworth
 */
public class GameObjectSerializer extends AbstractAdapter<GameObject>
{
    protected static final String TYPE = "type";
    protected static final String CLASS = "class";

    protected GameObjectFactory objectFactory;
    protected TypeRegistry typeRegistry;

    @Inject
    public GameObjectSerializer(GameObjectFactory objectFactory, TypeRegistry typeRegistry) {
        this.objectFactory = objectFactory;
        this.typeRegistry = typeRegistry;
    }

    @SerializedConstructor
    protected GameObjectSerializer() {
        GameService service = GameService.getInstance();
        this.objectFactory = service.getItemFactory();
        this.typeRegistry = service.getTypeRegistry();
    }

    @Override
    protected JsonObject getSerializedType(GameObject target, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty(CLASS, typeRegistry.getName(target.getClass()));
        result.add(TYPE, context.serialize(target.getType(), GameObjectType.class));
        return result;
    }

    @Override
    protected boolean isSerializedField(GameObject target, Field field) {
        return !Modifier.isTransient(field.getModifiers()) && !Objects.equals(field.getName(), TYPE);
    }

    @Override
    protected Class<?> getDeserializedType(JsonObject json, JsonDeserializationContext context) {
        if (json.has(CLASS)) {
            String name = json.get(CLASS).getAsString();
            return typeRegistry.getType(name);
        }
        return GameObject.class;
    }

    @Override
    protected GameObject getDeserializedInstance(JsonObject json, JsonDeserializationContext context) {
        GameObjectType identifier = context.deserialize(json.get(TYPE), GameObjectType.class);
        return objectFactory.get(identifier);
    }

    @Override
    protected boolean isDeserializedField(String name, JsonElement value) {
        return true;
    }
}
